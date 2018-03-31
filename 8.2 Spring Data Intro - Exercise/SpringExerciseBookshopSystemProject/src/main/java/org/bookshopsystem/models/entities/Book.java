package org.bookshopsystem.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "copies")
    private Integer copies;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "edition_type")
    private String editionType;

    @Column(name = "price", scale = 2, precision = 19)
    private BigDecimal price;

    @Column
    private Date dateTime;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    public Book(String ageRestriction, Integer copies, String description, String editionType, BigDecimal price, Date dateTime, String title, Author author, Set<Category> categories) {
        this.ageRestriction = ageRestriction;
        this.copies = copies;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.dateTime = dateTime;
        this.title = title;
        this.author = author;
        this.categories = categories;
    }

    public Book() {
        this.categories = new HashSet<>();
    }

    public Long getBookId() {
        return bookId;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditionType() {
        return editionType;
    }

    public void setEditionType(String editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
