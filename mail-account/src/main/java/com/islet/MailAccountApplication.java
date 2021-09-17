package com.islet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.islet.mapper")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MailAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailAccountApplication.class, args);
	}

}
