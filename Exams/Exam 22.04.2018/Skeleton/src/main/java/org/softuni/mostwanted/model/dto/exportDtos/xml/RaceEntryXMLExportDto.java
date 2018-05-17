package org.softuni.mostwanted.model.dto.exportDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryXMLExportDto {

    @XmlElement(name = "finish-time")
    private Double finishTime;
    @XmlElement(name = "car")
    private String car;

    public RaceEntryXMLExportDto(Double finishTime, String car) {
        this.finishTime = finishTime;
        this.car = car;
    }

    public RaceEntryXMLExportDto() {
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
