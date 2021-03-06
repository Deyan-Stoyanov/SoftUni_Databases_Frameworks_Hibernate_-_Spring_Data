package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class AnimalAidJSONImportDTO implements Serializable {
    @Expose
    @NotNull
    @Size(min = 3)
    private String name;

    @Expose
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    public AnimalAidJSONImportDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public AnimalAidJSONImportDTO() {
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
}
