package com.example.spa.config;

import com.example.spa.common.security.CustomAccessDeniedHandler;
import com.example.spa.common.security.CustomUserDetailService;
import com.example.spa.common.security.RestAuthenticationEntryPoint;
import com.example.spa.common.security.filter.JwtAuthenticationFilter;
import com.example.spa.common.security.filter.JwtRequestFilter;
import com.example.spa.common.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
// 시큐리티 애너테이션 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config..");

        http.formLogin().disable()
                .httpBasic().disable();

        http.cors();

        http.csrf().disable();

        // JWT 관련 필터 보안 컨텍스트에 추가와 세션 STATELESS 설정
        http.addFilterAt(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtRequestFilter(jwtTokenProvider),UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasAnyRole("ADMIN")
                .antMatchers("/").permitAll()
                .antMatchers("/codes/**").permitAll()
                .antMatchers("/codes/**").access("permitAll")
                .antMatchers("/users/**").access("permitAll")
                .antMatchers("/codegroups/**").access("hasRole('ADMIN')")
                .antMatchers("/codedetail/**").access("hasRole('ADMIN')")
                .antMatchers("/boards/**").access("request.method == 'GET' ? permitAll : hasAnyRole('MEMBER', 'ADMIN' )")
                .antMatchers("/notices/**").access("request.method == 'GET' ? permitAll : hasRole('ADMIN')")
                .antMatchers("/items/**").access("request.method == 'GET' ? permitAll : hasRole('ADMIN')")
                .antMatchers("/coins/**").access("hasRole('MEMBER')")
                .antMatchers("/useritems/**").access("hasAnyRole('MEMBER','ADMIN')")
                .antMatchers("/pds/**").access("request.method == 'GET ? permitAll : hasRole('ADMIN')")
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 스프링 시큐리티의 UserDetailService 를 구현한 service
    @Bean
    public UserDetailsService customUserDetailService(){
        return new CustomUserDetailService();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
