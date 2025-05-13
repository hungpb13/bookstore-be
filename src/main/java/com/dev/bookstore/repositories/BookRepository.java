package com.dev.bookstore.repositories;

import com.dev.bookstore.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
