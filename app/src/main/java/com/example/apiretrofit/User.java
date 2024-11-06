package com.example.apiretrofit;

import com.google.gson.annotations.SerializedName;

public class User {

    private String id;
    private String name;
    private String email;

    public User(String name, String emails){
        this.name=name;
        this.email=emails;
    }



    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

