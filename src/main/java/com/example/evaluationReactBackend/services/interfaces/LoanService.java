package com.example.evaluationReactBackend.services.interfaces;

import com.example.evaluationReactBackend.entities.Loan;

import java.util.List;

public interface LoanService {
    Loan saveLoan(Loan loan);
    Loan getLoanById(Long id);
    List<Loan> getAllLoans();
    void updateLoan(Loan loan);
    void deleteLoan(Long id);
}