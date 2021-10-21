package com.acmebank.accountmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class AccountManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagerApplication.class, args);
	}

}
