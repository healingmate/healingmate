package com.mallang.healingmate.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * com.mallang.healingmate.common.config
 * AppProperties.java
 * @date    2021-05-01 오후 8:34
 * @author  이아영
 *
 * @변경이력
 **/

@Getter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();

    @Getter
    @Setter
    public static class Auth{
        private String tokenSecret;
        private Long AccessTokenExpirationMsec;
        private Long RefreshTokenExpirationMsec;
    }
}
