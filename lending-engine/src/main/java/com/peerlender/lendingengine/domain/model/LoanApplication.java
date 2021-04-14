package com.peerlender.lendingengine.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.util.Objects;

@Entity
public final class LoanApplication {

    @Id
    @GeneratedValue
    private long id;
    private int amount;
    @ManyToOne
    private User borrower;
    private int repaymentTerm;

    public LoanApplication() {
    }

    public long getId() {
        return id;
    }

    private Double interestRate;

    public LoanApplication(int amount, User borrower, int repaymentTerm, Double interestRate) {
        this.amount = amount;
        this.borrower = borrower;
        this.repaymentTerm = repaymentTerm;
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", borrower=" + borrower +
                ", repaymentTerm=" + repaymentTerm +
                ", interestRate=" + interestRate +
                '}';
    }

    public Money getAmount() {
        return new Money(Currency.INR,amount);
    }

    public User getBorrower() {
        return borrower;
    }

    public int getRepaymentTerm() {
        return repaymentTerm;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplication that = (LoanApplication) o;
        return amount == that.amount && Objects.equals(borrower, that.borrower) && Objects.equals(repaymentTerm, that.repaymentTerm) && Objects.equals(interestRate, that.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, borrower, repaymentTerm, interestRate);
    }
}
