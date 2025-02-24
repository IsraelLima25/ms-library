package br.com.ilima.library.api.catalog;

import br.com.ilima.library.api.catalog.client.BookCatalogClient;
import io.micrometer.core.instrument.MeterRegistry;
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

    final MeterRegistry meterRegistry;

    @RestClient
    BookCatalogClient bookCatalogClient;

    public HttpServiceCatalogImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    // TODO: verify fault tolerance
    @Override
    public List<BookCatalogResponse> findBookCatalog(BookCatalogRequest bookCatalogRequest) {

        try{
            Log.info("Start request");
            meterRegistry.counter("query_catalog_find_success").increment();
            return bookCatalogClient.findBookCatalog(bookCatalogRequest.description(), bookCatalogRequest.location());

        }catch (WebApplicationException e){
            meterRegistry.counter("error_query_catalog").increment();
            Response response = e.getResponse();
            Log.errorf("HTTP Error: %d - %s", response.getStatus(), response.getStatusInfo().getReasonPhrase());
            throw new RuntimeException("Error while fetching book catalog: " + response.getStatus(), e);
        }catch (Exception e){
            meterRegistry.counter("unexpected_error_occurred").increment();
            Log.error("Unexpected error occurred", e);
            throw new RuntimeException("Unexpected error while fetching book catalog", e);
        }
    }
}
