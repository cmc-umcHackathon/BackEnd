package com.example.hackathon.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .addServersItem(new Server().url("/"));
    }

    private Info apiInfo() {
        return new Info()
                .title("너디너리 해커톤 A팀")
                .description("오늘의 실천 팀입니다.")
                .version("1.0.0");
    }
}
