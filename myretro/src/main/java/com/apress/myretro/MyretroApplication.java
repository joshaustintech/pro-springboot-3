package com.apress.myretro;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class MyretroApplication {

	public static void main(String[] args) {
		final var springApplication = new SpringApplication(MyretroApplication.class);
		springApplication.run(args);
	}

}
