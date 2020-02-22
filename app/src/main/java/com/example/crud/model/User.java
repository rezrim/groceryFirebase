package com.example.crud.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String key;

    public User(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String toString() {
        return " "+username+"\n" +
                " "+password;
    }

    public User(String un, String ps){
        username = un;
        password = ps;
    }
}
