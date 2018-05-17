package org.softuni.mostwanted.model.dto.importDtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownJSONImportDto {
    @Expose
    @NotNull
    private String name;

    public TownJSONImportDto(String name) {
        this.name = name;
    }

    public TownJSONImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
