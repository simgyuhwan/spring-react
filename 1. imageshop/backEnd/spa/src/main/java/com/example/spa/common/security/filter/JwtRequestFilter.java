package com.example.spa.common.security.filter;

import com.example.spa.common.security.jwt.constants.SecurityConstants;
import com.example.spa.common.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    // request 의 header 값을 가져와 Token 헤더값이 맞는지 확인하고 맞다면 다음 필터로 전달한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더의 "Authorization" 값을 가져온다.
        String header = request.getHeader(SecurityConstants.TOKEN_HEADER);

        // 헤더 값이 비어있는지 올바른 JWT TOKEN 이 맞는지 유효성 검사를 한다.
        if(isEmpty(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
            filterChain.doFilter(request, response);
            return;
        }

        // 검증
        Authentication authentication = jwtTokenProvider.getAuthentication(header);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private boolean isEmpty(final CharSequence cs){
        return cs == null || cs.length() == 0;
    }
}
