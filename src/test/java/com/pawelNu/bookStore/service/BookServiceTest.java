package com.pawelNu.bookStore.service;

import com.pawelNu.bookStore.dto.BookDto;
import com.pawelNu.bookStore.model.BookEntity;
import com.pawelNu.bookStore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void shouldReturnListOfBookDtoWhenGetBooksCalled() {
        List<BookEntity> books = new ArrayList<>();
        BookEntity book = getBook();
        books.add(book);
        BookDto bookDto = getBookDto();

        when(bookRepository.findAll()).thenReturn(books);
        when(mapper.map(book, BookDto.class))
                .thenReturn(bookDto);

        List<BookDto> bookDtos = bookService.getBooks();

        assertThat(bookDtos.size()).isEqualTo(1);
        assertThat(bookDtos.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("title", "test title")
                .hasFieldOrPropertyWithValue("description", "test description")
                .hasFieldOrPropertyWithValue("releaseYear", 2020);
    }

    private BookEntity getBook() {
        return BookEntity.builder()
                .id(UUID.randomUUID())
                .title("test title")
                .description("test description")
                .releaseYear(2020)
                .build();

    }

    private BookDto getBookDto() {
        return BookDto.builder()
                .id(UUID.randomUUID())
                .title("test title")
                .description("test description")
                .releaseYear(2020)
                .build();
    }
}
