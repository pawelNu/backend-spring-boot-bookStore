package com.pawelNu.bookStore.repository;

import com.pawelNu.bookStore.model.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:insert_initial_book_record_for_test.sql"})
    void shouldAbleToFetchAllBooksInDB() {
        Iterable<BookEntity> all = bookRepository.findAll();
        Long totalBookCount =
                StreamSupport.stream(all.spliterator(), false).count();
        Assertions.assertEquals(2, totalBookCount);
    }

    @Test
    @Sql(scripts = {"classpath:insert_initial_book_record_for_test.sql"})
    void shouldReturnOneBookWhenTitleIsTestTitle() {
        List<BookEntity> testTitle =
                bookRepository.findBooksByTitle("test title");
        Assertions.assertEquals(1, testTitle.size());
    }
}
