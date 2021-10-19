package com.rmit.sept.bk_loginservices.model;

public class UserUpdateRequest {

    private User oldUser;
    private User newUser;
    
    public UserUpdateRequest(User oldUser, User newUser) {
        this.oldUser = oldUser;
        this.newUser = newUser;
    }

    public User getOldUser() {
        return oldUser;
    }

    public User getNewUser() {
        return newUser;
    }

}
