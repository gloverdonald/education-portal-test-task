package com.mst.security.filter;

import com.mst.dto.response.UserResponse;
import com.mst.service.TokenAuthenticationService;
import com.mst.utils.HttpResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = parseToken((HttpServletRequest) request);
            if (Objects.nonNull(token)) {
                UserResponse userResponse = tokenAuthenticationService.getUserInfoByToken(token);
                PreAuthenticatedAuthenticationToken authenticationToken =
                        new PreAuthenticatedAuthenticationToken(userResponse, token);
                if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else if (!SecurityContextHolder.getContext().getAuthentication().getCredentials().equals(token)) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            chain.doFilter(request, response);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            HttpResponseUtil.putExceptionInResponse(((HttpServletRequest) request), ((HttpServletResponse) response),
                    exception, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String parseToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("BEARER ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}