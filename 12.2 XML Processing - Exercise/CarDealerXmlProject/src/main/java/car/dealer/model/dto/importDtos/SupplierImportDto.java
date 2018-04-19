package car.dealer.model.dto.importDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierImportDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "is-importer")
    private String isImporter;

    public SupplierImportDto(String name, String isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public SupplierImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String isImporter() {
        return isImporter;
    }

    public void setImporter(String importer) {
        isImporter = importer;
    }
}

