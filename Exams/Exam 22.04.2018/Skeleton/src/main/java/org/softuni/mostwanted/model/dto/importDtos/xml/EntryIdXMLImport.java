package org.softuni.mostwanted.model.dto.importDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class EntryIdXMLImport {

    @XmlAttribute(name = "id")
    private Integer id;

    public EntryIdXMLImport(Integer id) {
        this.id = id;
    }

    public EntryIdXMLImport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
