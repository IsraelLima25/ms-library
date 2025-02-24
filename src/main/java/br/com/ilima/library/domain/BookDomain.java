package br.com.ilima.library.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_book")
public class BookDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "name_author")
    private String nameAuthor;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private BigDecimal price;

    public BookDomain(){ }

    public BookDomain(String description, String nameAuthor, String location, BigDecimal price) {
        this.description = description;
        this.nameAuthor = nameAuthor;
        this.location = location;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getLocation() {
        return location;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
