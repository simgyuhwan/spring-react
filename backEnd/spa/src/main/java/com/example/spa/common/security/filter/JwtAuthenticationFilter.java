package com.example.spa.common.security.filter;

import com.example.spa.common.security.domain.CustomUser;
import com.example.spa.common.security.jwt.constants.SecurityConstants;
import com.example.spa.common.security.provider.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.spa.common.security.jwt.constants.SecurityConstants.TOKEN_HEADER;
import static com.example.spa.common.security.jwt.constants.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider){
        this.authenticationManager= authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }


    // 인증 전 (처음 접속)
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authentication 이 UsernamePasswordAuthenticationToken 의 조상이기 때문에 담을 수 있다.
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 매니저에 위에 받아온 토큰을 전달해서 인증을 시도한다. 만약 잘못된 계정이라면 에러를 던지고 올바른 계정이라면 다시 반환해준다.
        return authenticationManager.authenticate(authenticationToken);
    }

    // 인증 후
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 성공적인 인증 후의 authResult.getPrincipal 에는 위에서 CustomUser 값이 들어있게 된다. 그 안에는 Member 를 필드값으로 넣어줬다.
        CustomUser user = ((CustomUser)authResult.getPrincipal());
        long userNo = user.getUserNo();
        String userId = user.getUserId();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // userNo,userId, roles 값을 사용해서 Token 을 생성한다.
        String token = jwtTokenProvider.createToken(userNo, userId, roles);

        // 이제 토큰 값을 응답 헤더의 Bearer 뒤에 넣어준당
        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + token);
    }
}
