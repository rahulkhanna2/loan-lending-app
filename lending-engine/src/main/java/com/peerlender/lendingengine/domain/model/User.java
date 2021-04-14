package com.peerlender.lendingengine.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public final class User {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String occupation;
    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    public User() {
    }

    public User(String username, String firstName, String lastName, int age, String occupation, Balance balance) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.occupation = occupation;
        this.balance = balance;
    }

    public void topUp(final Money money) {
        balance.topUp(money);
    }

    public void withdraw(final Money money) {
        balance.withdraw(money);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }


    public String getUsername() {
        return username;
    }

    public Balance getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(occupation, user.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, age, occupation);
    }
}
