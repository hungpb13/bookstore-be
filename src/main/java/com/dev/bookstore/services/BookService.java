package com.dev.bookstore.services;

import com.dev.bookstore.domain.requests.BookSummary;
import com.dev.bookstore.domain.responses.BookResponse;

public interface BookService {
    BookResponse createAndFullUpdateBook(String isbn, BookSummary bookSummary);
}
