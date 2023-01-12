package com.example.kursovaya2;

public class User {
    public String id, name, surname, mail, password;
    public Boolean admin;

    public User(){

    }

    public User (String id, String name, String surname, String mail, String password, Boolean admin){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.admin = admin;
    }
}
