package com.dev.bookstore.controllers;

import com.dev.bookstore.domain.dtos.BookSummaryDto;
import com.dev.bookstore.domain.dtos.BookUpdateRequestDto;
import com.dev.bookstore.domain.entities.Book;
import com.dev.bookstore.domain.requests.BookSummary;
import com.dev.bookstore.domain.requests.BookUpdateRequest;
import com.dev.bookstore.domain.responses.BookResponse;
import com.dev.bookstore.mappers.impl.BookMapper;
import com.dev.bookstore.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @PutMapping(path = "/{isbn}")
    public ResponseEntity<BookSummaryDto> createAndFullUpdateBook(
            @PathVariable String isbn,
            @RequestBody BookSummaryDto bookSummaryDto
    ) {
        try {
            BookSummary bookSummary = bookMapper.toBookSummary(bookSummaryDto);
            BookResponse bookResponse = bookService.createAndFullUpdateBook(isbn, bookSummary);
            Book savedBook = bookResponse.getBook();
            boolean isCreated = bookResponse.isCreate();
            HttpStatus responseCode = isCreated ? HttpStatus.CREATED : HttpStatus.OK;
            return new ResponseEntity<>(bookMapper.toBookSummaryDto(savedBook), responseCode);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<BookSummaryDto> readManyBooks(@Nullable @RequestParam("author") Long authorId) {
        List<Book> books = bookService.readManyBooks(authorId);
        return books.stream()
                .map(bookMapper::toBookSummaryDto)
                .toList();
    }

    @GetMapping(path = "/{isbn}")
    public ResponseEntity<BookSummaryDto> readOneBook(@PathVariable String isbn) {
        try {
            Book book = bookService.readOneBook(isbn);
            return new ResponseEntity<>(bookMapper.toBookSummaryDto(book), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/{isbn}")
    public ResponseEntity<BookSummaryDto> partialUpdateBook(
            @PathVariable String isbn,
            @RequestBody BookUpdateRequestDto bookUpdateRequestDto
    ) {
        try {
            BookUpdateRequest bookUpdateRequest = bookMapper.toBookUpdateRequest(bookUpdateRequestDto);
            Book updatedBook = bookService.partialUpdateBook(isbn, bookUpdateRequest);
            return new ResponseEntity<>(bookMapper.toBookSummaryDto(updatedBook), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
