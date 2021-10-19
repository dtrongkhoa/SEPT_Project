package com.rmit.sept.bookCatalogServices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Review {

    @Id
    private String uuid;
    @Column(unique = true)
    private String username;
    private String title;
    private String body;
    private int likes;
    private int rating;
    private String ISBN;
    @Column(name = "TIMESTAMP")
    private Timestamp timeStamp;

    public Review() { super(); }

    public Review(String uuid, String username, String title, String body, int likes, int rating, String ISBN, Timestamp timeStamp) {
        this.uuid = uuid;
        this.username = username;
        this.title = title;
        this.body = body;
        this.likes = likes;
        this.rating = rating;
        this.ISBN = ISBN;
        this.timeStamp = timeStamp;
    }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public int getLikes() { return likes; }

    public void setLikes(int likes) { this.likes = likes; }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }

    public String getISBN() { return ISBN; }

    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public Timestamp getTimeStamp() { return timeStamp; }

    public void setTimeStamp(Timestamp timeStamp) { this.timeStamp = timeStamp; }

    @Override
    public String toString() {
        return String.format("Username: %s | Title: %s | Body: %s | Likes: %d | Rating: %d", username, title, body, likes, rating);
    }

}
