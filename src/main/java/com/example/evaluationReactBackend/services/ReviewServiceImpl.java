package com.example.evaluationReactBackend.services;

import com.example.evaluationReactBackend.dto.responses.ReviewResponse;
import com.example.evaluationReactBackend.entities.Review;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.mappers.ReviewMapper;
import com.example.evaluationReactBackend.repositories.ReviewRepository;
import com.example.evaluationReactBackend.services.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review saveReview(Review review) {
        try {
            return reviewRepository.save(review);
        } catch (Exception e) {
            throw new RuntimeException("Error saving review", e);
        }
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with ID: " + id));
        return ReviewMapper.INSTANCE.toDto(review);
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(ReviewMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateReview(Review updatedReview) {
        Review existingReview = reviewRepository.findById(updatedReview.getId())
                .orElseThrow(() -> new EntityNotFoundException("Review not found with ID: " + updatedReview.getId()));

        existingReview.setComment(updatedReview.getComment());
        existingReview.setStars(updatedReview.getStars());
        existingReview.setDate(updatedReview.getDate());

        reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Review not found with ID: " + id);
        }
    }
}
