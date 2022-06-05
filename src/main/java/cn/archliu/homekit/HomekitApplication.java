package cn.archliu.homekit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@MapperScan(basePackages = { "cn.archliu.homekit.domain.*.mapper" })
@SpringBootApplication
public class HomekitApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomekitApplication.class, args);
	}

}
