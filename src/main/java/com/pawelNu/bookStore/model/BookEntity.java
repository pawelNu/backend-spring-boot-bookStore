package com.pawelNu.bookStore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    private UUID id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private int releaseYear;
}
