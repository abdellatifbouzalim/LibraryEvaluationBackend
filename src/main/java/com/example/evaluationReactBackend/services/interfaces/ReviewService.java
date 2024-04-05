package com.example.evaluationReactBackend.services.interfaces;

import com.example.evaluationReactBackend.entities.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(Review review);
    Review getReviewById(Long id);
    List<Review> getAllReviews();
    void updateReview(Review review);
    void deleteReview(Long id);
}
