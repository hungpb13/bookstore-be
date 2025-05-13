package com.dev.bookstore.mappers.impl;

import com.dev.bookstore.domain.dtos.AuthorDto;
import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorMapper implements Mapper<Author, AuthorDto> {

    private final ModelMapper modelMapper;

    @Override
    public Author toEntity(AuthorDto dto) {
        return modelMapper.map(dto, Author.class);
    }

    @Override
    public AuthorDto toDto(Author entity) {
        return modelMapper.map(entity, AuthorDto.class);
    }
}
