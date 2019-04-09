package com.example.blog;

public class ModelUsers {

    private  String email;
    private String username;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }



    public ModelUsers(String email, String username) {
    this.email = email;
    this.username = username;
    }


}
