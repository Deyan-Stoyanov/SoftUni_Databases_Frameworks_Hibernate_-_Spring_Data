package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLImportDTO {

    @XmlElement
    private String vet;

    public ProcedureAnimalAidXMLImportDTO(String vet) {
        this.vet = vet;
    }

    public ProcedureAnimalAidXMLImportDTO() {
    }

    public String getVet() {
        return vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }
}
