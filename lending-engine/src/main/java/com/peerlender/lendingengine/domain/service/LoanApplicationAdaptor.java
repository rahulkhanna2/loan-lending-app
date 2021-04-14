package com.peerlender.lendingengine.domain.service;

import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class LoanApplicationAdaptor {

    private final UserRepository userRepo;


    @Autowired
    public LoanApplicationAdaptor(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public LoanApplication transform(LoanRequest req, User borrower) {
        Optional<User> user = userRepo.findById(borrower.getUsername());
        if (user.isPresent()) {
            return new LoanApplication(req.getAmount(), user.get(), req.getDaysToRepay(), req.getInterestRate());
        } else {
            throw new UserNotFoundException(borrower.getUsername());
        }
    }

}
