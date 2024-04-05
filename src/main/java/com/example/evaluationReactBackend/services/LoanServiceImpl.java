package com.example.evaluationReactBackend.services;

import com.example.evaluationReactBackend.entities.Loan;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.repositories.LoanRepository;
import com.example.evaluationReactBackend.services.interfaces.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with ID: " + id));
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public void updateLoan(Loan loan) {
        Optional<Loan> existingLoan = loanRepository.findById(loan.getId());
        if (existingLoan.isEmpty()) {
            throw new EntityNotFoundException("Loan not found with ID: " + loan.getId());
        }
        loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isPresent()) {
            loanRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Loan not found with ID: " + id);
        }
    }
}
