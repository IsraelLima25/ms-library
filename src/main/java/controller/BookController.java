package controller;

import controller.input.BookRequest;
import io.quarkus.logging.Log;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;
import service.BookService;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @POST
    public RestResponse<Void> add(@Valid BookRequest bookRequest, @Context UriInfo uri){
        Log.info("Start request add book name "+bookRequest.description());
        bookService.add(bookRequest);
        Log.info("Book "+bookRequest.description()+" add success");
        return RestResponse.created(uri.getAbsolutePath());
    }

    @GET
    public void find(){ }
}
