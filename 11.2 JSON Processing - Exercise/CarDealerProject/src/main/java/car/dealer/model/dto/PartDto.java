package car.dealer.model.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private int quantity;
    @Expose
    private Long supplier;

    public PartDto(String name, BigDecimal price, int quantity, Long supplier) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public PartDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }
}

