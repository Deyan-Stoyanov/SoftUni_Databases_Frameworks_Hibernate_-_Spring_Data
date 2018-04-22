package hiberspring.dtos.views.branch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class BranchViewDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String town;
    @XmlAttribute(name = "total-clients")
    private Long totalClients;

    public BranchViewDto(String name, String town, Long totalClients) {
        this.name = name;
        this.town = town;
        this.totalClients = totalClients;
    }

    public BranchViewDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(Long totalClients) {
        this.totalClients = totalClients;
    }
}

