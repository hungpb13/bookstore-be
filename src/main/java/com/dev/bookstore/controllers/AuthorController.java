package com.dev.bookstore.controllers;

import com.dev.bookstore.domain.dtos.AuthorDto;
import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.mappers.impl.AuthorMapper;
import com.dev.bookstore.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        Author authorToCreate = authorMapper.toEntity(authorDto);
        Author createdAuthor = authorService.createAuthor(authorToCreate);
        AuthorDto createdAuthorDto = authorMapper.toDto(createdAuthor);
        return new ResponseEntity<>(createdAuthorDto, HttpStatus.CREATED);
    }
}
