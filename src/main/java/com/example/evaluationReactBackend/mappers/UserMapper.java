package com.example.evaluationReactBackend.mappers;

import com.example.evaluationReactBackend.dto.requests.UserRequest;
import com.example.evaluationReactBackend.dto.responses.UserResponse;
import com.example.evaluationReactBackend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(User user);
    UserResponse toEntity(UserRequest userRequest);

}
