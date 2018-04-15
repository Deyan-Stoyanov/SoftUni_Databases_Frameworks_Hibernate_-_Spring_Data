package car.dealer.model.dto;

import com.google.gson.annotations.Expose;

public class CarForSaleDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long distanceTravelled;

    public CarForSaleDto(String make, String model, Long distanceTravelled) {
        this.make = make;
        this.model = model;
        this.distanceTravelled = distanceTravelled;
    }

    public CarForSaleDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(Long distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
