package org.softuni.mostwanted.model.dto.exportDtos.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class RacerExportXmlDto {
    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<RaceEntryXMLExportDto> entries;

    public RacerExportXmlDto(String name, List<RaceEntryXMLExportDto> entries) {
        this.name = name;
        this.entries = entries;
    }

    public RacerExportXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RaceEntryXMLExportDto> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntryXMLExportDto> entries) {
        this.entries = entries;
    }
}
