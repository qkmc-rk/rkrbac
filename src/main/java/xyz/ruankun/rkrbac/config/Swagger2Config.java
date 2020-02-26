package xyz.ruankun.rkrbac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: mrruan
 * @date: 2018/11/7 22:31
 * @description:
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("rkrbac")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("xyz.ruankun.rkrbac"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("mrruan", "http://www.ruankun.xyz", "qkmc@outlook.com");
        return new ApiInfoBuilder()
                .title("RBAC权限开发脚手架Restful API文档")
                .description("关于作者")
                .termsOfServiceUrl("https://github.com/qkmc-rk")
                .contact(contact)
                .version("v1.1")
                .build();
    }
}
