package car.dealer.model.dto.exportDtos;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewModel {
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute
    private String travelledDistance;
    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
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
