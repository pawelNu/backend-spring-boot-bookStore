package com.pawelNu.bookStore.integrationTest;

import com.pawelNu.bookStore.BookStoreApplication;
import com.pawelNu.bookStore.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BookStoreApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql(scripts = {"classpath:insert_initial_book_record_for_test.sql"})
    void shouldReturnBooksWhenBookApiCalled1() {
        BookDto[] listOfBooks = testRestTemplate
                .getForObject("http://localhost:" + port + "/api/v1/books",
                BookDto[].class);

        assertThat(listOfBooks).isNotNull();
        assertThat(listOfBooks.length).isEqualTo(2);
    }

    @Test
//    @Sql(scripts = {"classpath:insert_initial_book_record_for_test.sql"})
    //@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    // don't clean database before each method
    void shouldReturnBooksWhenBookApiCalled2() {
        BookDto[] listOfBooks = testRestTemplate
                .getForObject("http://localhost:" + port + "/api/v1/books",
                        BookDto[].class);

        assertThat(listOfBooks).isNotNull();
        assertThat(listOfBooks.length).isEqualTo(2);
    }
}
