package com.example.apiretrofit;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String id;

@SerializedName("name")
    private String names;
@SerializedName("email")
    private String emails;

    public User(String name, String emails){
        this.names=name;
        this.emails=emails;
    }


    public String getId() { return id; }
    public String getName() { return names; }
    public String getEmail() { return emails; }

    public void setEmail(String email) {
        this.emails = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.names = name;
    }
}

