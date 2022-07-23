package com.mst.security.details;

import com.mst.dto.response.UserResponse;
import com.mst.exception.UserNotFoundException;
import com.mst.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        return loadUserDetails((UserResponse) token.getPrincipal(), String.valueOf(token.getCredentials()));
    }

    private UserDetails loadUserDetails(UserResponse userResponse, String token) {
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userResponse.getRole().toString()));
        return UserDetailsImpl.builder()
                .id(userResponse.getId())
                .username(userResponse.getEmail())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .userEntity(userRepository.findByEmail(userResponse.getEmail()).orElseThrow(UserNotFoundException::new))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .authorities(authorities)
                .token(token)
                .build();
    }
}