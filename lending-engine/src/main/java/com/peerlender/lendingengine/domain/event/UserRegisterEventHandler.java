package com.peerlender.lendingengine.domain.event;

import com.google.gson.Gson;
import com.peerlender.lendingengine.domain.model.Balance;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterEventHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static Gson gson = new Gson();
    private final UserRepository userRepository;

    @Autowired
    public UserRegisterEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handleUserRegistration(String userDetails) {
        User user = gson.fromJson(userDetails, User.class);
        User updatedUser = new User(user.getUsername(),user.getFirstName(), user.getLastName(), user.getAge(), user.getOccupation(), new Balance());
        userRepository.save(updatedUser);
        logger.info("User {} saved into database !!", user.getUsername());

    }
}
