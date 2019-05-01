package canarin.lowfare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
            "Jetvu",
            "This is a Spring Boot Rest API microservice in Java divided into a 3 layers: Controller, Service and Repository. JUnit and Mockito are used for tests. \n" +
                "Lombok is used to reduce boilerplate. JPA is used to save flights to the MySQL database. Flight search results are live and retrieved from Amadeus.",
            "1.0",
            "",
            new Contact("Jakša Tomović","jaksatomovic.github.io","jaksa.tomovic@gmail.com"),
            "",
            "",
            Collections.emptyList()
        );
    }
}

