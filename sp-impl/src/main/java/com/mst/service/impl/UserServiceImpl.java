package com.mst.service.impl;

import com.mst.dto.request.*;
import com.mst.dto.response.FileResponse;
import com.mst.dto.response.UserResponse;
import com.mst.enums.Role;
import com.mst.exception.*;
import com.mst.mapper.UserMapper;
import com.mst.model.ConfirmCodeEntity;
import com.mst.model.FileEntity;
import com.mst.model.UserEntity;
import com.mst.repository.UserRepository;
import com.mst.security.details.UserDetailsImpl;
import com.mst.service.ConfirmCodeService;
import com.mst.service.FileService;
import com.mst.service.UserService;
import com.mst.utils.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailUtil emailUtil;

    private final ConfirmCodeService confirmCodeService;

    private final FileService fileService;

    @Value("${email.confirm.url}")
    private String emailUrl;

    @Override
    public UserResponse createUser(SignUpRequest signUpRequest) {
        log.info("User create: {}", signUpRequest.getEmail());
        userRepository.findByEmail(signUpRequest.getEmail()).ifPresent(u -> {
            if (Boolean.TRUE.equals(u.getVerified())) {
                log.info("User already exist: {} ", signUpRequest.getEmail());
                throw new UserAlreadyExistsException();
            }
        });
        UserEntity user = userRepository.findByEmail(signUpRequest.getEmail()).
                orElse(userMapper.toEntity(signUpRequest));


        String newConfirmCode = UUID.randomUUID().toString();
        user.setConfirmCode(ConfirmCodeEntity.builder().confirmCode(newConfirmCode).build());

        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("confirmLink", emailUrl + newConfirmCode);
        emailUtil.sendMail(user.getEmail(), "confirmation", "confirm_mail", data);

        user.setRole(Role.USER);
        user.setHashPassword("NOT VERIFIED");

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse login(SignInRequest signInRequest) {
        log.info("User login: {}", signInRequest.getEmail());
        UserEntity user = userRepository.findByEmail(signInRequest.getEmail())
                .filter(u -> passwordEncoder.matches(signInRequest.getPassword(), u.getHashPassword()))
                .orElseThrow(() -> new UnauthorizedException("Can't login in: " + signInRequest.getEmail() + ". Wrong email or password."));
        if (!user.getVerified()) {
            log.warn("User is not confirmed");
            throw new ConfirmEmailException();
        }
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse confirm(String confirmCode, NewPasswordRequest passwordRequest) {
        ConfirmCodeEntity confirmCodeEntity = confirmCodeService.getEntityByConfirmCode(confirmCode);
        UserEntity user = confirmCodeEntity.getUser();
        log.info("User confirm: {}", user.getEmail());
        user.setHashPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        user.setVerified(true);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getById(Long id) {
        log.info("User get: {}", id.toString());
        return userMapper.toResponse(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public FileResponse getAvatarByUserId(Long id) {
        log.info("User get avatar: {}", id.toString());
        FileEntity avatar = userRepository.findById(id).orElseThrow(UserNotFoundException::new).getAvatar();
        if (avatar == null) {
            throw new FileNotFoundException();
        }
        return fileService.getResponseById(avatar.getId());
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        log.info("User update: {}", user.getEmail());
        userMapper.update(user, userRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        log.info("User save: {}", userEntity.getEmail());
        return userRepository.save(userEntity);
    }

    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest, UserDetailsImpl userDetailsImpl) {
        UserEntity user = userDetailsImpl.getUserEntity();
        log.info("User update password: {}", user.getEmail());
        if (!passwordEncoder.matches(passwordUpdateRequest.getOldPassword(), user.getHashPassword())) {
            throw new WrongOldPasswordException();
        }
        user.setHashPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }
}
