package br.com.ilima.library.api.catalog;

import java.util.List;

public interface HttpServiceCatalog {
    List<BookCatalogResponse> findBookCatalog(BookCatalogRequest bookCatalogRequest);
}
