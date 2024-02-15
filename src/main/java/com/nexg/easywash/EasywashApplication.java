package com.nexg.easywash;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nexg.easywash.mapper")
public class EasywashApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasywashApplication.class, args);
	}

}
