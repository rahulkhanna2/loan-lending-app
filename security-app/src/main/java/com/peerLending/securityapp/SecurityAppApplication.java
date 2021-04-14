package com.peerLending.securityapp;

import com.peerLending.securityapp.user.model.User;
import com.peerLending.securityapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityAppApplication implements CommandLineRunner {

	private UserRepository userRepository;

	@Autowired
	public SecurityAppApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("john", ""));
		userRepository.save(new User("henry", ""));
	}
}
