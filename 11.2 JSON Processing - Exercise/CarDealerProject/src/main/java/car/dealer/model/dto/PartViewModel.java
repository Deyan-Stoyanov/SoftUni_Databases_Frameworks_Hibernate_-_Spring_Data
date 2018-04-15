package car.dealer.model.dto;

import com.google.gson.annotations.Expose;

public class PartViewModel {

    @Expose
    private String name;
    @Expose
    private String price;

    public PartViewModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public PartViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
