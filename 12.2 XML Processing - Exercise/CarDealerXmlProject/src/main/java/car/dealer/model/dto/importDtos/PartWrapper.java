package car.dealer.model.dto.importDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(value = XmlAccessType.FIELD)

public class PartWrapper {
    @XmlElement(name = "part")
    List<PartImportDto> parts;

    public PartWrapper(List<PartImportDto> parts) {
        this.parts = parts;
    }

    public PartWrapper() {
    }

    public List<PartImportDto> getParts() {
        return parts;
    }

    public void setParts(List<PartImportDto> parts) {
        this.parts = parts;
    }
}
