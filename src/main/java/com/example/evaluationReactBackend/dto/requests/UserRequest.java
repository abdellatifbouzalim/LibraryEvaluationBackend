package com.example.evaluationReactBackend.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequest (

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    String username,
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    String email
){}
