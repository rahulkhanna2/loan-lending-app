package com.peerLending.profile.application;

import com.peerLending.profile.domain.model.User;
import com.peerLending.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileContoller {

    private final UserRepository userRepository;

    @Autowired
    public ProfileContoller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);

    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
        userRepository.save(user);
    }


}
