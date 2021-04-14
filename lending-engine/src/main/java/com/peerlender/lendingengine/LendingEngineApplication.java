package com.peerlender.lendingengine;

import com.peerlender.lendingengine.domain.model.Balance;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LendingEngineApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LendingEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("john", "John", "Keneddy", 27, "SDE1", new Balance()));
		userRepository.save(new User("henry", "Henry", "Liber", 18, "Student",new Balance()));

	}
}
