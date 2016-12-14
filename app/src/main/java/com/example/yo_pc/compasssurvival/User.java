package com.example.yo_pc.compasssurvival;

public class User {

    private String name;
    private int record;

    public User() {
        this.name = "Name doesn't established";
        record = 0;
    }

    /*public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecord(){
        return record;
    }

    public void setRecord(){
        this.record = record;
    }
}