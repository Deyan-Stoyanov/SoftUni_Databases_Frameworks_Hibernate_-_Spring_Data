package org.softuni.mostwanted.model.dto.importDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryXmlWrapper {
    @XmlElement(name = "race-entry")
    private List<RaceEntryXmlImportDto> raceEntries;

    public RaceEntryXmlWrapper(List<RaceEntryXmlImportDto> raceEntries) {
        this.raceEntries = raceEntries;
    }

    public RaceEntryXmlWrapper() {
    }

    public List<RaceEntryXmlImportDto> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntryXmlImportDto> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
