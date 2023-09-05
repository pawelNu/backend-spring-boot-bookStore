package com.pawelNu.bookStore.controller;

import com.pawelNu.bookStore.dto.BookDto;
import com.pawelNu.bookStore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // http://localhost:8080/api/v1/books
    @GetMapping("")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }
}
