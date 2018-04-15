package app.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonsDto {

    @XmlElement(name = "person")
    @Expose
    private List<PersonDto> personDtos;

    public PersonsDto(List<PersonDto> personDtos) {
        this.personDtos = personDtos;
    }

    public PersonsDto() {
    }

    public List<PersonDto> getPersonDtos() {
        return personDtos;
    }

    public void setPersonDtos(List<PersonDto> personDtos) {
        this.personDtos = personDtos;
    }
}
