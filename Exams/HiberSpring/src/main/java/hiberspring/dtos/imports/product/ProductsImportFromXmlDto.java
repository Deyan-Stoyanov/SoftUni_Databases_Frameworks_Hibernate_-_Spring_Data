package hiberspring.dtos.imports.product;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportFromXmlDto {
    @XmlAttribute
    @NotNull
    private String name;
    @XmlAttribute
    @NotNull
    private Integer clients;
    @XmlElement
    @NotNull
    private String branch;

    public ProductsImportFromXmlDto(String name, Integer clients, String branch) {
        this.name = name;
        this.clients = clients;
        this.branch = branch;
    }

    public ProductsImportFromXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
