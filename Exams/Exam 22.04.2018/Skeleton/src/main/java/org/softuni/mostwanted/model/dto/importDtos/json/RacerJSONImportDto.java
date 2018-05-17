package org.softuni.mostwanted.model.dto.importDtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RacerJSONImportDto {
    @Expose
    @NotNull
    private String name;

    @Expose
    private Integer age;

    @Expose
    private BigDecimal bounty;

    @Expose
    private String homeTown;

    public RacerJSONImportDto(String name, Integer age, BigDecimal bounty, String townName) {
        this.name = name;
        this.age = age;
        this.bounty = bounty;
        this.homeTown = townName;
    }

    public RacerJSONImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
