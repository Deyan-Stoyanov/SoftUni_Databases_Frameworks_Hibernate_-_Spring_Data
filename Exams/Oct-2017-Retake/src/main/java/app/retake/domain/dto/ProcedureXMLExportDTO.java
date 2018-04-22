package app.retake.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureXMLExportDTO {
    @XmlAttribute(name = "animal-passport")
    private String animalPassport;
    @XmlElement(name = "owner")
    private String ownerPhoneNumber;
    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aids")
    private List<ProcedureAnimalAidXMLExportDTO> animalAids;

    public ProcedureXMLExportDTO(String animalPassport, String ownerPhoneNumber, List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalPassport = animalPassport;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.animalAids = animalAids;
    }

    public ProcedureXMLExportDTO() {
    }

    public String getAnimalPassport() {
        return animalPassport;
    }

    public void setAnimalPassport(String animalPassport) {
        this.animalPassport = animalPassport;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public List<ProcedureAnimalAidXMLExportDTO> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalAids = animalAids;
    }
}
