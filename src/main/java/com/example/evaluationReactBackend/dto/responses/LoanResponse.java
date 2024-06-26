package com.example.evaluationReactBackend.dto.responses;

import java.time.LocalDate;


public record LoanResponse (
        Long id,
        LocalDate borrowDate,
        BookResponse book,
        UserResponse borrowingUser

){}