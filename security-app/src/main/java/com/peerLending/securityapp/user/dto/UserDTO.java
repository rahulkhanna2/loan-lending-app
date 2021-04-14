package com.peerLending.securityapp.user.dto;

import javax.persistence.Id;
import java.time.LocalDate;

public class UserDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String occupation;

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public UserDTO(String username, String password, String firstName, String lastName, int age, String occupation) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.occupation = occupation;
    }
}
