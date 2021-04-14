package com.peerLending.securityapp.application;

import com.peerLending.securityapp.user.dto.UserDTO;
import com.peerLending.securityapp.user.exception.UserNotFoundException;
import com.peerLending.securityapp.user.model.User;
import com.peerLending.securityapp.user.repository.UserRepository;
import com.peerLending.securityapp.user.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Autowired
    public UserController(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDto){
        User user = new User(userDto.getUsername(), userDto.getPassword());
        userRepository.save(user);
        notificationService.sendMessage(userDto);
    }

    @PostMapping(value = "/validate")
    public String validateToken(@RequestHeader("Authorization") String token){
        return userRepository.findById(token).orElseThrow(() -> new UserNotFoundException()).getUsername();
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


}
