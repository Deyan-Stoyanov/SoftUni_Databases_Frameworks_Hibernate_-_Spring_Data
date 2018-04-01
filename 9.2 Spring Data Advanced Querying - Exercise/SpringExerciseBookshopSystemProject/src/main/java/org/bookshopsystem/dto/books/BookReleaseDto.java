package org.bookshopsystem.dto.books;

import java.math.BigDecimal;

public class BookReleaseDto {

    private String title;
    private String editionType;
    private String ageRestriction;
    private BigDecimal price;

    public BookReleaseDto(String title, String editionType, String ageRestriction, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    public BookReleaseDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditionType() {
        return editionType;
    }

    public void setEditionType(String editionType) {
        this.editionType = editionType;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getTitle() + " " + this.getEditionType() + " " + this.getAgeRestriction() + " " + this.getPrice();
    }
}
