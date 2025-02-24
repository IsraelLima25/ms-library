package br.com.ilima.library.api.catalog;

import java.math.BigDecimal;

public record BookCatalogResponse(
    String description,
    String nameAuthor,
    String location,
    BigDecimal price
) {}
