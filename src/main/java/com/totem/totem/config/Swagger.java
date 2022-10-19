package com.totem.totem.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket SwaggerAPI() {
        ParameterBuilder paramBuilder = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        paramBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false)
                .build();
        params.add(paramBuilder.build());

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.globalOperationParameters(params).select()
                .apis(RequestHandlerSelectors.basePackage("com.totem.totem.controller")).paths(PathSelectors.any())
                .build().apiInfo(this.detalhesAPI().build())
                .consumes(new HashSet<String>(Arrays.asList("application/json")))
                .produces(new HashSet<String>(Arrays.asList("application/json")));

        return docket;
    }

    private ApiInfoBuilder detalhesAPI() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Totem - API");
        apiInfoBuilder.description("API Totem");
        apiInfoBuilder.version("0.0.1");
        apiInfoBuilder.licenseUrl("https://github.com/renan2911/");
        apiInfoBuilder.license("Licença - CORTEI");

        return apiInfoBuilder;

    }
}
