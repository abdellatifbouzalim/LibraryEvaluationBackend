package com.example.evaluationReactBackend.services.interfaces;

import com.example.evaluationReactBackend.dto.responses.ReviewResponse;
import com.example.evaluationReactBackend.entities.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(Review review);
    ReviewResponse getReviewById(Long id);
    List<ReviewResponse> getAllReviews();
    void updateReview(Review review);
    void deleteReview(Long id);
}
