package one.digitalInnovation.parking.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@Component
public class SwaggerConfig {

    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("one.digitalInnovation.parking"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {

        ApiInfo info = new ApiInfo(
                "Atividades API REST",
                "API REST de cadastro de atividades.",
                "1.0",
                "Terms of Service",
                "www.teste.com",
                "Vitor Hugo",
                "");
        return info ;

                /*.title("Parking REST API")
                .description("Spring Boot REST API for Parking")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();*/
    }


}
