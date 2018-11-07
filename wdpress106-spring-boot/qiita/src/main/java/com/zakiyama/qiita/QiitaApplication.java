package com.zakiyama.qiita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QiitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QiitaApplication.class, args);
	}
}
