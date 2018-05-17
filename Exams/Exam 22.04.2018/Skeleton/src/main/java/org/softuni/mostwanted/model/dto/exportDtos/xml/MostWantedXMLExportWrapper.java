package org.softuni.mostwanted.model.dto.exportDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class MostWantedXMLExportWrapper {
    @XmlElement(name = "racer")
    private RacerExportXmlDto racer;

    public MostWantedXMLExportWrapper(RacerExportXmlDto racer) {
        this.racer = racer;
    }

    public MostWantedXMLExportWrapper() {
    }

    public RacerExportXmlDto getRacer() {
        return racer;
    }

    public void setRacer(RacerExportXmlDto racer) {
        this.racer = racer;
    }
}
