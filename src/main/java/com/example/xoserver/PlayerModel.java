package com.example.xoserver;

public class PlayerModel {

    private String username ;
    private String password ;
    private String mail ;
    private String phone ;
    private String name ;


    public PlayerModel(String username, String password, String mail, String phone, String name) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
        this.name = name;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
