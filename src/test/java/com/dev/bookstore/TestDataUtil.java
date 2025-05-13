package com.dev.bookstore;

import com.dev.bookstore.domain.dtos.AuthorDto;
import com.dev.bookstore.domain.entities.Author;

public class TestDataUtil {

    public static AuthorDto createTestAuthorDto() {
        return AuthorDto.builder()
                .name("Author Name")
                .age(30)
                .description("This is a description.")
                .image("author-image.jpg")
                .build();
    }

    public static AuthorDto expectedTestAuthorDto() {
        return AuthorDto.builder()
                .id(1L)
                .name("Author Name")
                .age(30)
                .description("This is a description.")
                .image("author-image.jpg")
                .build();
    }

    public static Author createTestAuthor() {
        return Author.builder()
                .name("Author Name")
                .age(30)
                .description("This is a description.")
                .image("author-image.jpg")
                .build();
    }

    public static Author expectedTestAuthor(Long id) {
        return Author.builder()
                .id(id)
                .name("Author Name")
                .age(30)
                .description("This is a description.")
                .image("author-image.jpg")
                .build();
    }
}
