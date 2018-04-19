package car.dealer.model.dto.exportDtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartExportWrapper {
    @XmlElement(name = "car")
    private List<CarViewModel> cars;

    public CarPartExportWrapper(List<CarViewModel> cars) {
        this.cars = cars;
    }

    public CarPartExportWrapper() {
    }

    public List<CarViewModel> getCars() {
        return cars;
    }

    public void setCars(List<CarViewModel> cars) {
        this.cars = cars;
    }
}
