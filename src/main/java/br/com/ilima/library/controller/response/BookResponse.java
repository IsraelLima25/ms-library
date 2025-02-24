package br.com.ilima.library.controller.response;

import java.math.BigDecimal;

public record BookResponse(
    String description,
    String nameAuthor,
    String location,
    BigDecimal price
) {}
