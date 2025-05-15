package com.dev.bookstore.services.impl;

import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.repositories.AuthorRepository;
import com.dev.bookstore.services.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public Author fullUpdateAuthor(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        existingAuthor.setName(author.getName());
        existingAuthor.setAge(author.getAge());
        existingAuthor.setDescription(author.getDescription());
        existingAuthor.setImage(author.getImage());

        return authorRepository.save(existingAuthor);
    }

    @Override
    public Author partialUpdateAuthor(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        Optional.ofNullable(author.getName()).ifPresent(existingAuthor::setName);
        Optional.ofNullable(author.getAge()).ifPresent(existingAuthor::setAge);
        Optional.ofNullable(author.getDescription()).ifPresent(existingAuthor::setDescription);
        Optional.ofNullable(author.getImage()).ifPresent(existingAuthor::setImage);

        return authorRepository.save(existingAuthor);
    }
}
