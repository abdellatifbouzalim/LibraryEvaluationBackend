package com.example.evaluationReactBackend.dto;


public record ApiResponse(
        int statusCode,
        String message
) {}