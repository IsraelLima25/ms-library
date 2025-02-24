package br.com.ilima.library.api.catalog;

import br.com.ilima.library.api.exception.BookNotFoundException;
import io.quarkus.arc.properties.IfBuildProperty;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
@IfBuildProperty(name = "app.api.mock.enabled", stringValue = "true")
public class HttpServiceCatalogMock implements HttpServiceCatalog {

    @Override
    public List<BookCatalogResponse> findBookCatalog(BookCatalogRequest bookCatalogRequest) {

        Log.info("Starter find book mock service");
        if(bookCatalogRequest.location().equals("RJ") && bookCatalogRequest.description().contains("quitetu")){
            Log.info("book mock service find success");
            return List.of(new BookCatalogResponse("Arquitetura Limpa", "Robert Martins", "RJ", new BigDecimal("130.00")));
        }
        Log.error("Book not found mock service find");
        throw new BookNotFoundException("Book not found!");
    }
}
