package com.netcracker.swagger;

import com.google.common.base.Predicates;
import io.swagger.annotations.*;
import io.swagger.models.*;
import io.swagger.models.Info;
import io.swagger.models.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ListVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.*;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }




    private ApiInfo metaData(){
   ApiInfo apiInfo = new ApiInfo(
           "Tire fit service",
           "Application for the users and services",
           "1.0",
           "https://github.com/EgorBochkarev/tire-fitting.git",
           new Contact("Contact name", "https://github.com/EgorBochkarev/tire-fitting.git", "contact@email.com"),
           "Apache license",
           "http://apache.org/licenses",
           new ArrayList<VendorExtension>());

    return apiInfo;
    }



}
