package com.example.springsecurityjwt;

import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class SecurityjwyApplication {

	@Autowired
	private UserRepository userRepository;
	//create method list user
	@PostConstruct
	public  void initUsers(){
		List<User> users = Stream.of(
				new User(101, "thama", "123","thama31@gmail.com"),
				new User(102, "aom31", "3143","aom31@gmail.com"),
		        new User(103, "nuti1", "31112","nuticha.nut@gmail.com"),
		        new User(104, "ketno43", "4332","ghe31_aom@hotmail.com"),
		        new User(105, "aomaom1", "aa123","aom11_tha@hotmail.com")
		).collect(Collectors.toList());
		userRepository.saveAll(users);

	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityjwyApplication.class, args);
	}

}
