package com.example.evaluationReactBackend.controllers;

import com.example.evaluationReactBackend.dto.ApiResponse;
import com.example.evaluationReactBackend.dto.responses.ReviewResponse;
import com.example.evaluationReactBackend.entities.Review;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.services.interfaces.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveReview(@Valid @RequestBody Review review) {
        try {
            reviewService.saveReview(review);
            ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), "Review created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error saving review");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        try {
            ReviewResponse reviewResponse = reviewService.getReviewById(id);
            return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Review not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        List<ReviewResponse> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateReview(@PathVariable Long id, @Valid @RequestBody Review review) {
        try {
            review.setId(id);
            reviewService.updateReview(review);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Review updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Review not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error updating review");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Review deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Review not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
