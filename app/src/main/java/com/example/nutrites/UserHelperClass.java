package com.example.nutrites;

public class UserHelperClass {
    String email, number, password, username;

    public UserHelperClass() {

    }


    public UserHelperClass(String email, String number, String username, String password ) {
        this.email = email;
        this.number = number;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
