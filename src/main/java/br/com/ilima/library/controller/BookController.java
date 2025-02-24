package br.com.ilima.library.controller;

import br.com.ilima.library.api.catalog.BookCatalogRequest;
import br.com.ilima.library.api.catalog.BookCatalogResponse;
import br.com.ilima.library.api.catalog.HttpServiceCatalog;
import br.com.ilima.library.controller.request.BookRequest;
import br.com.ilima.library.controller.response.BookResponse;
import io.quarkus.logging.Log;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;
import br.com.ilima.library.service.BookService;

import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    final BookService bookService;
    final HttpServiceCatalog httpServiceCatalog;

    public BookController(BookService bookService, HttpServiceCatalog httpServiceCatalog) {
        this.bookService = bookService;
        this.httpServiceCatalog = httpServiceCatalog;
    }

    @POST
    public RestResponse<Void> add(@Valid BookRequest bookRequest, @Context UriInfo uri){
        Log.info("Start request add book name "+bookRequest.description());
        bookService.add(bookRequest);
        Log.info("Book "+bookRequest.description()+" add success");
        return RestResponse.created(uri.getAbsolutePath());
    }

    @GET
    @Path("/my-store")
    public RestResponse<List<BookResponse>> findMyStore(@QueryParam("description") String description){
        List<BookResponse> listBookResponse = bookService.findMyStore(description);
        return RestResponse.ok(listBookResponse);
    }

    @GET
    @Path("/catalog-store")
    public RestResponse<List<BookCatalogResponse>> findCatalog(@QueryParam("description") String description,
                                                               @QueryParam("location") String location){
        var bookCatalogRequest = new BookCatalogRequest(description, location);
        List<BookCatalogResponse> bookCatalogResponse = httpServiceCatalog.findBookCatalog(bookCatalogRequest);
        return RestResponse.ok(bookCatalogResponse);
    }
}
