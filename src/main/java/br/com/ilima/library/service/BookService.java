package br.com.ilima.library.service;

import br.com.ilima.library.config.LibraryConfigProperties;
import br.com.ilima.library.controller.request.BookRequest;
import br.com.ilima.library.controller.response.BookResponse;
import br.com.ilima.library.domain.BookDomain;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import br.com.ilima.library.repository.BookRepository;

import java.util.List;

@ApplicationScoped
public class BookService {

    final BookRepository bookRepository;
    final LibraryConfigProperties libraryConfigProperties;

    public BookService(BookRepository bookRepository, LibraryConfigProperties libraryConfigProperties) {
        this.bookRepository = bookRepository;
        this.libraryConfigProperties = libraryConfigProperties;
    }

    @Transactional
    public void add(BookRequest bookRequest){
        BookDomain bookDomain = new BookDomain(bookRequest.description(), bookRequest.nameAuthor(),
                libraryConfigProperties.getLocationNow(), bookRequest.price());
        bookRepository.persist(bookDomain);
    }

    public List<BookResponse> findMyStore(String description){
        List<BookDomain> listBooksByDescription = bookRepository.findByDescription(description);
        return listBooksByDescription.stream().map(bookDomain -> {
            return new BookResponse(bookDomain.getDescription(),
                    bookDomain.getNameAuthor(), bookDomain.getLocation(),
                    bookDomain.getPrice());
        }).toList();
    }
}
