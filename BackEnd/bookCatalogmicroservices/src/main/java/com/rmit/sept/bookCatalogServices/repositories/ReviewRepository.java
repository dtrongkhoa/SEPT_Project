package com.rmit.sept.bookCatalogServices.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.rmit.sept.bookCatalogServices.model.Like;
import com.rmit.sept.bookCatalogServices.model.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@SuppressWarnings("SqlResolve")
@Repository
public class ReviewRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Review> getReviewByUUID(String UUID) {
        List<Review> review = jdbcTemplate.query("SELECT * FROM REVIEW WHERE UUID = ?", new Object[] {UUID}, new reviewRowMapper());
        return review;
    }

    public Review insertReview(Review review) {
        jdbcTemplate.update("INSERT INTO REVIEW (UUID, USERNAME, ISBN, TITLE, BODY, RATING, TIMESTAMP) VALUES (?, ?, ?, ?, ?, ?, ?)", review.getUuid(), review.getUsername(), review.getISBN(), review.getTitle(), review.getBody(), review.getRating(), review.getTimeStamp());
        return review;
    }

    public int deleteAllReviewsByUser(String username) {
        return jdbcTemplate.update("DELETE FROM REVIEW WHERE USERNAME = ?", username);
    }

    public int deleteReviewByUUID(String UUID) {
        return jdbcTemplate.update("DELETE FROM REVIEW WHERE UUID = ?", UUID);
    }

    public List<Review> getAllReviewsByBookISBN(String ISBN) {
        int likeCount;
        List<Review> list = jdbcTemplate.query("SELECT * FROM REVIEW WHERE ISBN = ?", new Object[] {ISBN}, new reviewRowMapper());
        for (Review review : list) {
            likeCount = getReviewLikeCount(review.getUuid());
            review.setLikes(likeCount);
        }
        return list;
    }

    public List<Review> getAllReviewsByUsername(String username) {
        List<Review> list = jdbcTemplate.query("SELECT * FROM REVIEW WHERE USERNAME = ?", new Object[] {username}, new reviewRowMapper());
        return list;
    }

    public int likeReview(Like like) {
        String sql = String.format("MERGE INTO LIKES AS T1 USING (SELECT '%s' UUID, '%s' USERNAME) AS T2 ON T1.UUID = T2.UUID AND T1.USERNAME = T2.USERNAME WHEN NOT MATCHED THEN INSERT VALUES(?, ?)", like.getUUID(), like.getUsername());
        return jdbcTemplate.update(sql, like.getUUID(), like.getUsername());
    }

    public int unlikeReview(Like like) {
        return jdbcTemplate.update("DELETE FROM LIKES WHERE UUID = ? AND USERNAME = ?", like.getUUID(), like.getUsername());
    }

    private int getReviewLikeCount(String UUID) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM LIKES WHERE UUID = ?", new Object[] {UUID}, Integer.class);
    }

}

class reviewRowMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs, int row) throws SQLException {
        Review review = new Review();
        review.setUuid(rs.getString("uuid"));
        review.setUsername(rs.getString("username"));
        review.setTimeStamp(rs.getTimestamp("timeStamp"));
        review.setTitle(rs.getString("title"));
        review.setBody(rs.getString("body"));
        review.setRating(rs.getInt("rating"));
        return review;
    }
}