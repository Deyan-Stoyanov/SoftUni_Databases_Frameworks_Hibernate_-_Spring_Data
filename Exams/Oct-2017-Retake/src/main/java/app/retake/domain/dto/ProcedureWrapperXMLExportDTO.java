package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "procedures")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureWrapperXMLExportDTO {
    @XmlElement(name = "procedure")
    private List<ProcedureXMLExportDTO> procedures;

    public ProcedureWrapperXMLExportDTO(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }

    public ProcedureWrapperXMLExportDTO() {
    }

    public List<ProcedureXMLExportDTO> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }
}
