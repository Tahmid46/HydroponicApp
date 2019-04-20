package com.example.tahmid.hydroponicapp;

public class Users {

    private String email,name,pass;

    public Users(String email,String name,String pass)
    {
        this.email=email;
        this.name=name;
        this.pass=pass;
    }

    public Users()
    {

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

}
