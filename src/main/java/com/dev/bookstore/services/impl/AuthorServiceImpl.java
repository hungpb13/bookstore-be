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
        if (author.getId() != null)
            throw new IllegalArgumentException("Cannot create author with an ID!");
        return authorRepository.save(author);
    }

    @Override
    public List<Author> readManyAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author readOneAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
}
