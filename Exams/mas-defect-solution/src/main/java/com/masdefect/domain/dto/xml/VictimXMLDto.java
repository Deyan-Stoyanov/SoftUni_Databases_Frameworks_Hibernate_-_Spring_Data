package com.masdefect.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class VictimXMLDto {
    @XmlAttribute(name = "name")
    private String name;

    public VictimXMLDto(String name) {
        this.name = name;
    }

    public VictimXMLDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
