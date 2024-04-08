package com.example.evaluationReactBackend.mappers;

import com.example.evaluationReactBackend.dto.responses.LoanResponse;
import com.example.evaluationReactBackend.entities.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    LoanResponse toDto(Loan loan);
}
