package com.peerlender.lendingengine.application.controller;

import com.peerlender.lendingengine.application.model.LoanRepaymentRequest;
import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.application.service.TokenValidationService;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.repository.LoanRepository;
import com.peerlender.lendingengine.domain.service.LoanApplicationAdaptor;
import com.peerlender.lendingengine.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoanController {


    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationAdaptor loanApplicationAdaptor;
    private final LoanService loanService;
    private final LoanRepository loanRepository;
    private final TokenValidationService tokenValidationService;

    @Autowired
    public LoanController(LoanApplicationRepository loanApplicationRepository, LoanApplicationAdaptor loanApplicationAdaptor, LoanService loanService, LoanRepository loanRepository, TokenValidationService tokenValidationService) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanApplicationAdaptor = loanApplicationAdaptor;
        this.loanService = loanService;
        this.loanRepository = loanRepository;
        this.tokenValidationService = tokenValidationService;
    }

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody final LoanRequest loanRequest, HttpServletRequest request) {
        User borrower = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanApplicationRepository.save(loanApplicationAdaptor.transform(loanRequest, borrower));

    }

    @GetMapping(value = "/loan/requests")
    public List<LoanApplication> getApplications(HttpServletRequest request) {
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanApplicationRepository.findAll();
    }

    @PostMapping(value = "/loan/accept/{applicationId}")
    public void acceptLoan(@PathVariable long applicationId, HttpServletRequest request) {
        User lender = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanService.acceptLoan(applicationId, lender.getUsername());
    }

    @GetMapping(value = "/loans")
    public List<Loan> getAllAcceptedLoans(HttpServletRequest request) {
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanRepository.findAll();
    }

    @GetMapping(value = "/loan/borrowed")
    public List<Loan> findBorrowedLoans(@RequestHeader String authorization) {
        User borrower = tokenValidationService.validateTokenAndGetUser(authorization);
        return loanService.findAllBorrowedLoans(borrower);

    }

    @GetMapping(value = "/loan/lent")
    public List<Loan> findAllLentLoans(@RequestHeader String authorization) {
        User lender = tokenValidationService.validateTokenAndGetUser(authorization);
        return loanService.findAllLentLoans(lender);

    }

    @PostMapping(value = "/loan/repay")
    public void repayLoan(@RequestBody LoanRepaymentRequest request, @RequestHeader String authorization) {
        User borrower = tokenValidationService.validateTokenAndGetUser(authorization);
        loanService.repayLoan(request.getAmount(), request.getLoanId(), borrower);

    }


}
