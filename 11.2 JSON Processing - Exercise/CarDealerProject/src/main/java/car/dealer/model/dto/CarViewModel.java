package car.dealer.model.dto;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarViewModel {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private String travelledDistance;
    @Expose
    private List<PartViewModel> parts;

    public CarViewModel(String make, String model, String travelledDistance, List<PartViewModel> parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public CarViewModel() {
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

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartViewModel> getParts() {
        return parts;
    }

    public void setParts(List<PartViewModel> parts) {
        this.parts = parts;
    }
}
