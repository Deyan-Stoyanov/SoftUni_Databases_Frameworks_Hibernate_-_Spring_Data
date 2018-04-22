package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryExportDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "most-popular-item")
    private MostPopularItemDTO mostPopularItem;

    public CategoryExportDTO(String name, MostPopularItemDTO mostPopularItem) {
        this.name = name;
        this.mostPopularItem = mostPopularItem;
    }

    public CategoryExportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MostPopularItemDTO getMostPopularItem() {
        return mostPopularItem;
    }

    public void setMostPopularItem(MostPopularItemDTO mostPopularItem) {
        this.mostPopularItem = mostPopularItem;
    }
}
