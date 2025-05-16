package com.dev.bookstore.services;

import com.dev.bookstore.domain.entities.Book;
import com.dev.bookstore.domain.requests.BookSummary;
import com.dev.bookstore.domain.responses.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse createAndFullUpdateBook(String isbn, BookSummary bookSummary);

    List<Book> readManyBooks();
}
