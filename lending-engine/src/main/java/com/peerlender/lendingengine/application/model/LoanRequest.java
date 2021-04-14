package com.peerlender.lendingengine.application.model;

import java.util.Objects;

public class LoanRequest {

    private final int amount;
    private final int daysToRepay;
    private final Double interestRate;

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", daysToRepay=" + daysToRepay +
                ", interestRate=" + interestRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return amount == that.amount && daysToRepay == that.daysToRepay && Objects.equals(interestRate, that.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, daysToRepay, interestRate);
    }

    public int getAmount() {
        return amount;
    }

    public int getDaysToRepay() {
        return daysToRepay;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public LoanRequest(int amount, long borrowerId, int daysToRepay, Double interestRate) {
        this.amount = amount;
        this.daysToRepay = daysToRepay;
        this.interestRate = interestRate;
    }
}
