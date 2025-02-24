package br.com.ilima.library.api.catalog;

public record BookCatalogRequest(
    String description,
    String location
) {}
