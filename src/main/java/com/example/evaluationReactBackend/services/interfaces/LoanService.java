package com.example.evaluationReactBackend.services.interfaces;

import com.example.evaluationReactBackend.dto.responses.LoanResponse;
import com.example.evaluationReactBackend.entities.Loan;

import java.util.List;

public interface LoanService {
    Loan saveLoan(Loan loan);
    LoanResponse getLoanById(Long id);
    List<LoanResponse> getAllLoans();
    void updateLoan(Loan loan);
    void deleteLoan(Long id);
}