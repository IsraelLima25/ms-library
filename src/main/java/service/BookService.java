package service;

import controller.input.BookRequest;
import domain.BookDomain;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import repository.BookRepository;

@ApplicationScoped
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void add(BookRequest bookRequest){
        BookDomain bookDomain = new BookDomain(bookRequest.description(), bookRequest.nameAuthor(),
                bookRequest.location(), bookRequest.price());
        bookRepository.persist(bookDomain);
    }
}
