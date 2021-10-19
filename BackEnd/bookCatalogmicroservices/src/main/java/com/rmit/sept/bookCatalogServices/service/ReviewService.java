package com.rmit.sept.bookCatalogServices.service;

import com.rmit.sept.bookCatalogServices.exceptions.ReviewDoesNotExistException;
import com.rmit.sept.bookCatalogServices.model.Like;
import com.rmit.sept.bookCatalogServices.model.Review;
import com.rmit.sept.bookCatalogServices.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview (Review newReview) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String uuid = UUID.randomUUID().toString();
        newReview.setTimeStamp(timestamp);
        newReview.setUuid(uuid);
        return reviewRepository.insertReview(newReview);
    }

    public List<Review> getReviewsForBook (String ISBN) {
        return reviewRepository.getAllReviewsByBookISBN(ISBN);
    }

    @Transactional
    public boolean deleteReviewsByUsername (String username) {
        List<Review> list = reviewRepository.getAllReviewsByUsername(username);
        reviewRepository.deleteAllReviewsByUser(username);
        return !list.isEmpty();
    }

    public boolean insertLike (Like like) {
        int success;
        if (reviewRepository.getReviewByUUID(like.getUUID()).size() == 1) {
            success = reviewRepository.likeReview(like);
        } else {
            throw new ReviewDoesNotExistException();
        }
        return success != 0;
    }

    public boolean deleteLike (Like like) {
        int success;
        if (reviewRepository.getReviewByUUID(like.getUUID()).size() == 1) {
            success = reviewRepository.unlikeReview(like);
        } else {
            throw new ReviewDoesNotExistException();
        }
        return success != 0;
    }

}
