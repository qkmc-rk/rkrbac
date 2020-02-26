package xyz.ruankun.rkrbac.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: mrruan
 * @date: 2019-02-04 16:45
 * @description:
 */
@Configuration
@Slf4j
public class CliWebMvcConfig implements WebMvcConfigurer {

//    @Autowired
//    private PropertiesConfig schedulePropertiesConfig;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        log.info("初始化JwtInterceptor拦截器...");
//        String excludeUrls = schedulePropertiesConfig.getExcludeUrls();
//        String[] excludeUrlsArray = excludeUrls.split(",");
//    }

    @Bean
    public CorsFilter corsFilter() {
        log.info("初始化CorsFilter跨域...");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
