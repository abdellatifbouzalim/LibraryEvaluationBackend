package com.example.evaluationReactBackend.dto.responses;

import java.time.LocalDate;

public record ReviewResponse (

        Long id,
        String comment,
        int stars,
        LocalDate date,
        BookResponse book,
        UserResponse user

){}