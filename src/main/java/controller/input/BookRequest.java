package controller.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

//TODO: https://pt.quarkus.io/guides/validation

public record BookRequest(
        @NotBlank
        String description,

        @NotBlank
        String nameAuthor,

        @NotBlank
        String location,

        @NotNull
        @Positive
        BigDecimal price) {}
