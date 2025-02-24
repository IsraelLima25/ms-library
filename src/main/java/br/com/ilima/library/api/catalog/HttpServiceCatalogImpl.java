package br.com.ilima.library.api.catalog;

import br.com.ilima.library.api.catalog.client.BookCatalogClient;
import io.quarkus.arc.properties.IfBuildProperty;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
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

        try{
            Log.info("Start request");
            return bookCatalogClient.findBookCatalog(bookCatalogRequest.description(), bookCatalogRequest.location());

        }catch (WebApplicationException e){
            // Trata exceções geradas pela resposta HTTP (ex: 404, 500)
            Response response = e.getResponse();
            Log.errorf("HTTP Error: %d - %s", response.getStatus(), response.getStatusInfo().getReasonPhrase());
            throw new RuntimeException("Error while fetching book catalog: " + response.getStatus(), e);
        }catch (Exception e){
            Log.error("Unexpected error occurred", e);
            throw new RuntimeException("Unexpected error while fetching book catalog", e);
        }
    }
}
