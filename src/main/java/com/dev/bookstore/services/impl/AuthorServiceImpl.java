package com.dev.bookstore.services.impl;

import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.repositories.AuthorRepository;
import com.dev.bookstore.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> readManyAuthors() {
        return authorRepository.findAll();
    }
}
