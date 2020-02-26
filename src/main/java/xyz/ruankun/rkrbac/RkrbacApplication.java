package xyz.ruankun.rkrbac;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: mrruan
 * @date: 2019-02-11 16:02
 * @description:
 */
@SpringBootApplication
@MapperScan("xyz.ruankun.rkrbac.mapper")
@Slf4j
public class RkrbacApplication implements ApplicationRunner {

	@Value("${server.port}")
	public int port;

	@Value("${cn.xlbweb.swagger-url}")
	public String swaggerUrl;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Tomcat服务启动完成: http://localhost:{}", port);
		log.info("Swagger2 API文档: {}", swaggerUrl);
	}

	public static void main(String[] args) {
		SpringApplication.run(RkrbacApplication.class, args);
	}
}
