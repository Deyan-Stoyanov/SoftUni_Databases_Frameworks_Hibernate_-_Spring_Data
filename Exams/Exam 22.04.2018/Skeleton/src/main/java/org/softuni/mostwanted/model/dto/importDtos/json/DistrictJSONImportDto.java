package org.softuni.mostwanted.model.dto.importDtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DistrictJSONImportDto {

    @Expose
    @NotNull
    private String name;

    @Expose
    private String townName;

    public DistrictJSONImportDto(String name, String townName) {
        this.name = name;
        this.townName = townName;
    }

    public DistrictJSONImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
