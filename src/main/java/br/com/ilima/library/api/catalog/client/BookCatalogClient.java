package br.com.ilima.library.api.catalog.client;

import br.com.ilima.library.api.catalog.BookCatalogResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/books")
@RegisterRestClient(configKey = "catalog-api")
public interface BookCatalogClient {

    @GET
    List<BookCatalogResponse> findBookCatalog(@QueryParam("description") String description,
                                              @QueryParam("location") String location);
}
