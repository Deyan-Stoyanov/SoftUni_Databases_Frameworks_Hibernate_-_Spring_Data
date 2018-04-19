package car.dealer.model.dto.exportDtos;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class PartViewModel {

    @XmlAttribute
    private String name;
    @XmlAttribute
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
