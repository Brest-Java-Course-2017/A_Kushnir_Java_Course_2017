package com.epam.test.dao;

import java.util.Objects;

/**
 * Created by kushnir on 13.2.17.
 * This is our 1st bean class
 */
public class User {
    private Integer userID;
    private String login;
    private String password;
    private String description;

    public User() {
    }

    public User(String login, String password, String description){
        this.login = login;
        this.password = password;
        this.description = description;
    }

    public User(Integer userId, String login, String password, String description) {
        this.userID = userId;
        this.login = login;
        this.password = password;
        this.description = description;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, login, password, description);
    }
}
