package hiberspring.dtos.views.town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsViewDto {
    @XmlElement(name = "town")
    private List<TownViewDto> towns;

    public TownsViewDto(List<TownViewDto> towns) {
        this.towns = towns;
    }

    public TownsViewDto() {
    }

    public List<TownViewDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownViewDto> towns) {
        this.towns = towns;
    }
}
