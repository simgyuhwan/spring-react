package com.example.spa.common.security.provider;

import com.example.spa.prop.ShopProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final ShopProperties shopProperties;


}