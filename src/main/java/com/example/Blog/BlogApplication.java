package com.example.Blog;

import com.example.Blog.Models.User;
import com.example.Blog.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) { SpringApplication.run(BlogApplication.class, args); }

	@Bean
	CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder){
		return args -> {
			users.save(new User("user", encoder.encode("password"),"user@email.com", "ROLE_USER"));
			users.save(new User("admin",encoder.encode("password"), "admin@email.com", "ROLE_ADMIN"));
		};
	}

}
