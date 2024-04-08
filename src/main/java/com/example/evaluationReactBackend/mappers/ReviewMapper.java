package com.example.evaluationReactBackend.mappers;

import com.example.evaluationReactBackend.dto.responses.ReviewResponse;
import com.example.evaluationReactBackend.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewResponse toDto(Review review);
}
