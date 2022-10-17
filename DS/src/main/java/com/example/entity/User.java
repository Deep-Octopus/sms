package com.example.entity;

import com.example.annotation.TableName;
import com.example.annotation.Type;

@TableName("user")
public class User {
    @Type(name = "username",isKey = true)
    private String username = "";
    @Type(name = "nickname")
    private String nickname = "";
    @Type(name = "email")
    private String email = "";
    @Type(name = "password")
    private String password = "";

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String nickname, String email, String password) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
