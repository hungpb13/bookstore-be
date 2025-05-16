package com.dev.bookstore.mappers.impl;

import com.dev.bookstore.domain.dtos.AuthorSummaryDto;
import com.dev.bookstore.domain.dtos.BookDto;
import com.dev.bookstore.domain.dtos.BookSummaryDto;
import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.domain.entities.Book;
import com.dev.bookstore.domain.requests.AuthorSummary;
import com.dev.bookstore.domain.requests.BookSummary;
import com.dev.bookstore.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookDto> {

    private final ModelMapper modelMapper;

    private final AuthorMapper authorMapper;

    @Override
    public Book toEntity(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }

    @Override
    public BookDto toDto(Book entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    public Book bookSummaryToBook(BookSummary bookSummary, Author author) {
        return Book.builder()
                .isbn(bookSummary.getIsbn())
                .title(bookSummary.getTitle())
                .description(bookSummary.getDescription())
                .image(bookSummary.getImage())
                .author(author)
                .build();
    }

    public BookSummary toBookSummary(BookSummaryDto bookSummaryDto) {
        AuthorSummary authorSummary = authorMapper.toAuthorSummary(bookSummaryDto.getAuthor());
        return BookSummary.builder()
                .isbn(bookSummaryDto.getIsbn())
                .title(bookSummaryDto.getTitle())
                .description(bookSummaryDto.getDescription())
                .image(bookSummaryDto.getImage())
                .author(authorSummary)
                .build();
    }

    public BookSummaryDto toBookSummaryDto(Book entity) {
        AuthorSummaryDto authorSummaryDto = authorMapper.toAuthorSummaryDto(entity.getAuthor());

        return BookSummaryDto.builder()
                .isbn(entity.getIsbn())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .image(entity.getImage())
                .author(authorSummaryDto)
                .build();
    }
}
