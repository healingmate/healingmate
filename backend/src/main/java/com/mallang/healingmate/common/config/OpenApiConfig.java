package com.mallang.healingmate.common.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import java.util.Arrays;
import java.util.List;

/**
 * com.mallang.healingmate.common.config
 * OpenApiConfig.java
 * @date    2021-05-01 오후 8:39
 * @author  이아영
 *
 * @변경이력
 **/


@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion, @Value("${server.servlet.context-path}") String contextPath) {
        Info info = new Info().title("HealingMate").version(appVersion)
                .description("HealingMate 서비스를 위한 웹 애플리케이션 API입니다.")
                .termsOfService("https://swagger.io/terms/")
                .contact(new Contact().name("uykgnod").url("https://www.notion.so/15edfa1444d64a1ab7a3d30d9a046563").email("uykgnod1@gmail.com"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        Components components = new Components().addSecuritySchemes("Authorization",new SecurityScheme().name("Authorization").type(SecurityScheme.Type.HTTP).scheme("bearer"));


        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .components(components)
                .info(info);
    }
}