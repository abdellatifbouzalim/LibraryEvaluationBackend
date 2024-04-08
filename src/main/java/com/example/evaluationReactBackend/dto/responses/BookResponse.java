package com.example.evaluationReactBackend.dto.responses;


public record BookResponse(
        Long id,
        String title,
        String author,
        String genre,
        String summary
) {}


