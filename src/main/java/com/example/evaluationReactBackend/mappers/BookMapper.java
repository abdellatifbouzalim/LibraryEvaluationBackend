package com.example.evaluationReactBackend.mappers;

import com.example.evaluationReactBackend.dto.responses.BookResponse;
import com.example.evaluationReactBackend.entities.Book;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookResponse toDto(Book book);

}
