package com.example.ktp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 开启注解事务管理
@MapperScan("com.example.ktp.mapper")
public class KtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtpApplication.class, args);
	}

}
