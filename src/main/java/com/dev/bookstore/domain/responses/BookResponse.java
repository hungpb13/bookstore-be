package com.dev.bookstore.domain.responses;

import com.dev.bookstore.domain.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Book book;
    private boolean create;
}
