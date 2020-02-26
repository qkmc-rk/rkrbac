package xyz.ruankun.rkrbac.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: mrruan
 * @date: 2019-02-11 16:02
 * @description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "xyz.ruankun.rkrbac")
public class CliProperties {

    private String jwtSign;
    private String excludeUrls;
}
