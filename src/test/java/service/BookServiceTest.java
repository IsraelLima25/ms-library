package service;

import br.com.ilima.library.config.LibraryConfigProperties;
import br.com.ilima.library.controller.response.BookResponse;
import br.com.ilima.library.domain.BookDomain;
import br.com.ilima.library.repository.BookRepository;
import br.com.ilima.library.service.BookService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusTest
class BookServiceTest {

    @Inject
    BookService bookService;

    @InjectMock
    BookRepository bookRepository;

    @InjectMock
    LibraryConfigProperties libraryConfigProperties;

    @Test
    void sholdListBookMyStoreServiceWhenReturnListBook(){
        when(bookRepository.findByDescription("arq"))
                .thenReturn(List.of(new BookDomain("Clean arctecture",
                        "Robert", "BA", new BigDecimal("39.49"))));

        List<BookResponse> listBookResponse = bookService.findMyStore("arq");
        assertEquals(1, listBookResponse.size());

    }
}