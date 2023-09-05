package com.pawelNu.bookStore.service;

import com.pawelNu.bookStore.dto.BookDto;
import com.pawelNu.bookStore.model.BookEntity;
import com.pawelNu.bookStore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public List<BookDto> getBooks() {
        Iterable<BookEntity> all = bookRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .map(convertBookModelToBookDto())
                .collect(Collectors.toList());
    }

    private Function<BookEntity, BookDto> convertBookModelToBookDto() {
        return book -> modelMapper.map(book, BookDto.class);
    }
}
