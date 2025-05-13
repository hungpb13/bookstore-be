package com.dev.bookstore.services.impl;

import com.dev.bookstore.TestDataUtil;
import com.dev.bookstore.domain.entities.Author;
import com.dev.bookstore.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AuthorServiceImplTest {

    private final AuthorServiceImpl underTest;

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImplTest(AuthorServiceImpl underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Test
    public void testThatCreateAuthorPersistsTheAuthorInTheDB() {
        Author createdAuthor = underTest.createAuthor(TestDataUtil.createTestAuthor());

        Long id = createdAuthor.getId();

        assertThat(id).isNotNull();

        Author expected = TestDataUtil.expectedTestAuthor(id);

        Author recalledAuthor = authorRepository.findById(id).orElse(null);

        assertThat(recalledAuthor).isNotNull();
    }
}
