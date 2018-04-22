package hiberspring.dtos.views.town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownViewDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer population;
    @XmlAttribute
    private Long clients;

    public TownViewDto(String name, Integer population, Long clients) {
        this.name = name;
        this.population = population;
        this.clients = clients;
    }

    public TownViewDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Long getClients() {
        return clients;
    }

    public void setClients(Long clients) {
        this.clients = clients;
    }
}
