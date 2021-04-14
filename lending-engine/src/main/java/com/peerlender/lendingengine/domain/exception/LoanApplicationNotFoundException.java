package com.peerlender.lendingengine.domain.exception;

public class LoanApplicationNotFoundException  extends RuntimeException{
    public LoanApplicationNotFoundException(long applicationId) {
        super(String.format("Loan Applicaton Id: %d not found", applicationId));
    }
}
