package br.com.ilima.library.api.catalog;

import br.com.ilima.library.api.catalog.client.BookCatalogClient;
import io.quarkus.arc.properties.IfBuildProperty;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
@IfBuildProperty(name = "app.api.mock.enabled", stringValue = "false")
public class HttpServiceCatalogImpl implements HttpServiceCatalog {

    @RestClient
    BookCatalogClient bookCatalogClient;

    // TODO: verify fault tolerance
    @Override
    public List<BookCatalogResponse> findBookCatalog(BookCatalogRequest bookCatalogRequest) {
        Log.info("Start request");
        return bookCatalogClient.findBookCatalog(bookCatalogRequest.description(), bookCatalogRequest.location());
    }
}
