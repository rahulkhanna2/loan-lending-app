package com.peerlender.lendingengine.domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User borrower;
    @ManyToOne
    private User lender;
    @OneToOne(cascade = CascadeType.ALL)
    private Money loanAmount;
    private double interestRate;
    private LocalDate dateLent;
    private LocalDate dateDue;
    @OneToOne(cascade = CascadeType.ALL)
    private Money  amountRepayed;

    public Loan() {
    }

    public Loan(User lender, LoanApplication loanApplication){
        this.borrower = loanApplication.getBorrower();
        this.lender = lender;
        this.loanAmount = loanApplication.getAmount();
        this.interestRate = loanApplication.getInterestRate();
        this.dateLent = LocalDate.now();
        this.dateDue = LocalDate.now().plusDays(loanApplication.getRepaymentTerm());
        this.amountRepayed = Money.ZERO;
    }

    public void repay(final Money money){
        borrower.withdraw(money);
        lender.topUp(money);
        amountRepayed.add(money);
    }

    public Money getAmountOwed(){
        return loanAmount.times( 1 + interestRate / 100.0).minus(amountRepayed);
    }

    public int getId() {
        return id;
    }

    public User getBorrower() {
        return borrower;
    }

    public User getLender() {
        return lender;
    }

    public Money getAmountRepayed() {
        return amountRepayed;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", borrower=" + borrower +
                ", lender=" + lender +
                ", loanAmount=" + loanAmount +
                ", interestRate=" + interestRate +
                ", dateLent=" + dateLent +
                ", dateDue=" + dateDue +
                ", amountRepayed=" + amountRepayed +
                '}';
    }
}
