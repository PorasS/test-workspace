package com.example.test.gitplugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class GitPluginApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GitPluginApplication.class, args);
	}

}
