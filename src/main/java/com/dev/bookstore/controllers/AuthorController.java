package com.dev.bookstore.controllers;

import com.dev.bookstore.domain.dtos.AuthorDto;
import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.mappers.impl.AuthorMapper;
import com.dev.bookstore.services.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        try {
            Author authorToCreate = authorMapper.toEntity(authorDto);
            Author createdAuthor = authorService.createAuthor(authorToCreate);
            AuthorDto createdAuthorDto = authorMapper.toDto(createdAuthor);
            return new ResponseEntity<>(createdAuthorDto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<AuthorDto> readManyAuthors() {
        return authorService.readManyAuthors()
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorDto> readOneAuthor(@PathVariable Long id) {
        return Optional.ofNullable(authorService.readOneAuthor(id))
                .map(foundAuthor ->
                        new ResponseEntity<>(authorMapper.toDto(foundAuthor), HttpStatus.OK)
                )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorDto authorDto
    ) {
        try {
            Author authorToUpdate = authorMapper.toEntity(authorDto);
            Author updatedAuthor = authorService.fullUpdateAuthor(id, authorToUpdate);
            return new ResponseEntity<>(authorMapper.toDto(updatedAuthor), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorDto authorDto
    ) {
        try {
            Author authorToUpdate = authorMapper.toEntity(authorDto);
            Author updatedAuthor = authorService.partialUpdateAuthor(id, authorToUpdate);
            return new ResponseEntity<>(authorMapper.toDto(updatedAuthor), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
