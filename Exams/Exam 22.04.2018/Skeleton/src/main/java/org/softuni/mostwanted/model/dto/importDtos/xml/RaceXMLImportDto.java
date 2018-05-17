package org.softuni.mostwanted.model.dto.importDtos.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceXMLImportDto {

    @XmlElement(name = "laps")
    @NotNull
    private Integer laps;

    @XmlElement(name = "district-name")
    @NotNull
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryIdXMLImport> entries;

    public RaceXMLImportDto(Integer laps, String districtName, List<EntryIdXMLImport> entries) {
        this.laps = laps;
        this.districtName = districtName;
        this.entries = entries;
    }

    public RaceXMLImportDto() {
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<EntryIdXMLImport> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryIdXMLImport> entries) {
        this.entries = entries;
    }
}
