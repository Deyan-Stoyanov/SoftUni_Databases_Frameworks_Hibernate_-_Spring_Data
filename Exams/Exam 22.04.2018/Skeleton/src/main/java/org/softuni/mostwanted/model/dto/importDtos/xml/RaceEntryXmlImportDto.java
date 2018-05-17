package org.softuni.mostwanted.model.dto.importDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.text.Format;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryXmlImportDto {
    @XmlAttribute(name = "has-finished")
    private Boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private Double finishTime;

    @XmlAttribute(name = "car-id")
    private Integer carId;

    @XmlElement(name = "racer")
    private String racer;

    public RaceEntryXmlImportDto(Boolean hasFinished, Double finishTime, Integer carId, String racer) {
        this.hasFinished = hasFinished;
        this.finishTime = finishTime;
        this.carId = carId;
        this.racer = racer;
    }

    public RaceEntryXmlImportDto() {
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getRacer() {
        return racer;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }
}
