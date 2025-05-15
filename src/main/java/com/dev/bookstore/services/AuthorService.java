package com.dev.bookstore.services;

import com.dev.bookstore.domain.entities.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);

    List<Author> readManyAuthors();

    Author readOneAuthor(Long id);

    Author fullUpdateAuthor(Long id, Author author);
}
