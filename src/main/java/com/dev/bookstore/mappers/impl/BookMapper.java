package com.dev.bookstore.mappers.impl;

import com.dev.bookstore.domain.dtos.BookDto;
import com.dev.bookstore.domain.entities.Book;
import com.dev.bookstore.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookDto> {

    private final ModelMapper modelMapper;

    @Override
    public Book toEntity(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }

    @Override
    public BookDto toDto(Book entity) {
        return modelMapper.map(entity, BookDto.class);
    }
}
