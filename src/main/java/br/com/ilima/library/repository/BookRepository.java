package br.com.ilima.library.repository;

import br.com.ilima.library.domain.BookDomain;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.annotations.Param;

import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepository<BookDomain> {

    public List<BookDomain> findByDescription(String description) {
        return find("description ILIKE ?1", "%" + description + "%").list();
    }
}
