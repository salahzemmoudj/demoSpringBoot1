package com.salah.nabila.example.demoSpringBoot1;

import org.apache.catalina.util.ServerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoSpringBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBoot1Application.class, args);
		System.out.println("Tomcat version: "+ServerInfo.getServerInfo());






	}

}
