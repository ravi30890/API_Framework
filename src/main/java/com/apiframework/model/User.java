package com.apiframework.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User(String uniqueid) {
        this.id = Integer.valueOf(uniqueid);
        this.userName = "USERNAME_" + uniqueid;
        this.firstName = "FIRSTNAME_" + uniqueid;
        this.lastName = "LASTNAME_" + uniqueid;
        this.email = "user_" + uniqueid + "@gmail.com";
        this.password = String.valueOf(uniqueid);
        this.phone = "1234567890";
        this.userStatus = 1;
    }
}
