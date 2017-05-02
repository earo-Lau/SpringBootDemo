package com.earo.test;

import com.earo.test.config.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication	//Spring Boot core annotation
public class SpringBootDemoApplication {

    @Value("${project.auth}")
    String projectAuth;
    @Value("${project.name}")
    String projectName;
    @Autowired
    AuthorSettings author;

	@RequestMapping("/")
	public String index(){
		return "Project name is " + projectName + ", Auth " + projectAuth;
	}

	@RequestMapping("/authorInfo")
	public String authTest(){
		return	"Author name: " + author.getName() + ", age: " + author.getAge();
	}

	public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootDemoApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run();
	}
}
