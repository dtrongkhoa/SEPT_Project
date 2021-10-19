package com.rmit.sept.bookCatalogServices.web;

import java.util.List;

import javax.validation.Valid;

import com.rmit.sept.bookCatalogServices.exceptions.LikeDoesNotExistException;
import com.rmit.sept.bookCatalogServices.exceptions.ReviewAlreadyLikedException;
import com.rmit.sept.bookCatalogServices.model.Like;
import com.rmit.sept.bookCatalogServices.model.Review;
import com.rmit.sept.bookCatalogServices.repositories.ReviewRepository;

import com.rmit.sept.bookCatalogServices.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/{ISBN}")
    public @ResponseBody ResponseEntity<List<Review>> getAllReviewsByBookISBN(@PathVariable String ISBN) {
        List<Review> results = reviewService.getReviewsForBook(ISBN);
        if (results.isEmpty()) {
            return new ResponseEntity<>(results, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Review> insertReview(@Valid @RequestBody Review review) {
        Review newReview = reviewService.addReview(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable String username) {
        boolean result = reviewService.deleteReviewsByUsername(username);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);

    }

    @PostMapping("/like")
    public ResponseEntity<Like> likeReview(@Valid @RequestBody Like like) {
        if (reviewService.insertLike(like)) {
            return new ResponseEntity<>(like, HttpStatus.CREATED);
        }
        throw new ReviewAlreadyLikedException();
    }

    @PostMapping("/unlike")
    public ResponseEntity<Like> unlikeReview(@Valid @RequestBody Like like) {
        if (reviewService.deleteLike(like)) {
            return new ResponseEntity<>(like, HttpStatus.OK);
        }
        throw new LikeDoesNotExistException();
    }

}
