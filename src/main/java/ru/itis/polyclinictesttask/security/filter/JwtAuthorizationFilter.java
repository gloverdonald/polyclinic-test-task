package ru.itis.polyclinictesttask.security.filter;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.security.service.TokenAuthorizationService;
import ru.itis.polyclinictesttask.security.util.HttpResponseUtil;

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
public class JwtAuthorizationFilter extends GenericFilterBean {

    private final TokenAuthorizationService tokenAuthorizationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = parseToken((HttpServletRequest) request);
            if (Objects.nonNull(token)) {
                AccountResponse userResponse = tokenAuthorizationService.getUserInfoByToken(token);
                PreAuthenticatedAuthenticationToken authenticationToken =
                        new PreAuthenticatedAuthenticationToken(userResponse, token);
                if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())
                        || !SecurityContextHolder.getContext().getAuthentication().getCredentials().equals(token)) {
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
