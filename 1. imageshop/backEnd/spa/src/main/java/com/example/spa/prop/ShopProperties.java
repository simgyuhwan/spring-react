package com.example.spa.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("shop")
public class ShopProperties {
    private String secretKey;
    private String uploadPath;
}
