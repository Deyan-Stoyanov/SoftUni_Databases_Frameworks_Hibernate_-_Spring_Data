package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemJSONImportDTO {
    @Expose
    @Size(min = 3, max = 30)
    private String name;
    @Expose
    @DecimalMin(value = "0.01")
    private BigDecimal price;
    @Expose
    @Size(min = 3, max = 30)
    private String category;

    public ItemJSONImportDTO() {
    }

    public ItemJSONImportDTO(String name, BigDecimal price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
