package com.peerLending.profile;

import com.peerLending.profile.domain.model.User;
import com.peerLending.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ProfileApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}

	private final UserRepository userRepository;

	@Autowired
	public ProfileApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("John", "John", "White", 23, "SDE"));
	}
}
