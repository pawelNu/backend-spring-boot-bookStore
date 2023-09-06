package com.pawelNu.bookStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "BookDto", description = "All details about book")
public class BookDto {

    @Schema(
            name = "id",
            accessMode = READ_ONLY,
            type = "UUID",
            example = "2df43aa1-55a7-4b62-9cbd-e408a02d66ab",
            description = "The database generated UUID for book id."
    )
    private UUID id;

    @Schema(
            name = "title",
            type = "String",
            example = "Example book title",
            description = "Book title.",
            requiredMode = REQUIRED
    )
    private String title;

    @Schema(
            name = "description",
            type = "String",
            example = "2df43aa1-55a7-4b62-9cbd-e408a02d66ab",
            description = "Book description.",
            requiredMode = REQUIRED
    )
    private String description;

    @Schema(
            name = "releaseYear",
            type = "int",
            example = "2020",
            description = "Book release year.",
            requiredMode = REQUIRED
    )
    private int releaseYear;
}
