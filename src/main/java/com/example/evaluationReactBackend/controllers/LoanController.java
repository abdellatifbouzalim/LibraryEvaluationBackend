package com.example.evaluationReactBackend.controllers;

import com.example.evaluationReactBackend.dto.ApiResponse;
import com.example.evaluationReactBackend.dto.responses.LoanResponse;
import com.example.evaluationReactBackend.entities.Loan;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.services.interfaces.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveLoan(@Valid @RequestBody Loan loan) {
        try {
            Loan savedLoan = loanService.saveLoan(loan);
            ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), "Loan created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error saving loan");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable Long id) {
        try {
            LoanResponse loanResponse = loanService.getLoanById(id);
            return new ResponseEntity<>(loanResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Loan not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<LoanResponse> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateLoan(@PathVariable Long id, @Valid @RequestBody Loan loan) {
        try {
            loan.setId(id);
            loanService.updateLoan(loan);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Loan updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Loan not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error updating loan");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLoan(@PathVariable Long id) {
        try {
            loanService.deleteLoan(id);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Loan deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Loan not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
