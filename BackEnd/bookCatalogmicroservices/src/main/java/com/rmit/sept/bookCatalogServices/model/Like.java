package com.rmit.sept.bookCatalogServices.model;

import java.io.Serializable;

public class Like implements Serializable {

    private String UUID;
    private String username;

    public Like() { super(); }

    public Like(String UUID, String username) {
        this.UUID = UUID;
        this.username = username;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
