package com.example.evaluationReactBackend.services;

import com.example.evaluationReactBackend.dto.responses.LoanResponse;
import com.example.evaluationReactBackend.dto.responses.UserResponse;
import com.example.evaluationReactBackend.entities.Loan;
import com.example.evaluationReactBackend.entities.User;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.mappers.LoanMapper;
import com.example.evaluationReactBackend.mappers.UserMapper;
import com.example.evaluationReactBackend.repositories.LoanRepository;
import com.example.evaluationReactBackend.services.interfaces.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public LoanResponse getLoanById(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with ID: " + id));
        return LoanMapper.INSTANCE.toDto(loan);
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream()
                .map(LoanMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateLoan(Loan loan) {
        Loan existingLoan = loanRepository.findById(loan.getId())
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with ID: " + loan.getId()));

        existingLoan.setBorrowDate(loan.getBorrowDate());
        existingLoan.setBorrowingUser(loan.getBorrowingUser());
        existingLoan.setBook(loan.getBook());

        loanRepository.save(existingLoan);
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
