package com.pawelNu.bookStore.repository;

import com.pawelNu.bookStore.model.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, UUID> {
    List<BookEntity> findBooksByTitle(String title);
}
