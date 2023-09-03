package com.pawelNu.bookStore.repository;

import com.pawelNu.bookStore.model.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends CrudRepository<BookEntity, UUID> {
    List<BookEntity> findBooksByTitle(String title);
}
