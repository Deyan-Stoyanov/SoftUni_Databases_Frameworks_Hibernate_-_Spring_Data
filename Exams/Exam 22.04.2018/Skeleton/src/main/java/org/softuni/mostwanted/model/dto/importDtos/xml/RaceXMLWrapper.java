package org.softuni.mostwanted.model.dto.importDtos.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceXMLWrapper {

    @XmlElement(name = "race")
    private List<RaceXMLImportDto> races;

    public RaceXMLWrapper(List<RaceXMLImportDto> races) {
        this.races = races;
    }

    public RaceXMLWrapper() {
    }

    public List<RaceXMLImportDto> getRaces() {
        return races;
    }

    public void setRaces(List<RaceXMLImportDto> races) {
        this.races = races;
    }
}
