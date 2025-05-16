package com.dev.bookstore.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSummaryDto {
    private String isbn;
    private String title;
    private String description;
    private String image;
    private AuthorSummaryDto author;
}
