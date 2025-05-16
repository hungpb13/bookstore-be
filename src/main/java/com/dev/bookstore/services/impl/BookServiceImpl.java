package com.dev.bookstore.services.impl;

import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.domain.entities.Book;
import com.dev.bookstore.domain.requests.BookSummary;
import com.dev.bookstore.domain.responses.BookResponse;
import com.dev.bookstore.mappers.impl.BookMapper;
import com.dev.bookstore.repositories.AuthorRepository;
import com.dev.bookstore.repositories.BookRepository;
import com.dev.bookstore.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final BookMapper bookMapper;

    @Override
    public BookResponse createAndFullUpdateBook(String isbn, BookSummary bookSummary) {
        bookSummary.setIsbn(isbn);

        boolean exists = bookRepository.existsById(isbn);

        Author author = authorRepository.findById(bookSummary.getAuthor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        Book bookToSave = bookMapper.bookSummaryToBook(bookSummary, author);

        Book savedBook = bookRepository.save(bookToSave);

        return BookResponse.builder()
                .book(savedBook)
                .create(!exists)
                .build();
    }

    @Override
    public List<Book> readManyBooks() {
        return bookRepository.findAll();
    }
}
