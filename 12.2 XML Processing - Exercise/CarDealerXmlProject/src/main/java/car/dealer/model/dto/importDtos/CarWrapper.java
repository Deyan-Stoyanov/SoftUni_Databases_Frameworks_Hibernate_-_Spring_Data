package car.dealer.model.dto.importDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarWrapper {

    @XmlElement(name = "car")
    List<CarXmlImportDto> cars;

    public CarWrapper(List<CarXmlImportDto> cars) {
        this.cars = cars;
    }

    public CarWrapper() {
    }

    public List<CarXmlImportDto> getCars() {
        return cars;
    }

    public void setCars(List<CarXmlImportDto> cars) {
        this.cars = cars;
    }
}
