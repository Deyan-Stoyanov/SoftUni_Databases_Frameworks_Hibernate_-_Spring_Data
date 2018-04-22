package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class StarImportJSONDto implements Serializable {
    @Expose
    @NotNull
    private String name;
    @Expose
    @NotNull
    private String solarSystem;

    public StarImportJSONDto(String name, String solarSystem) {
        this.name = name;
        this.solarSystem = solarSystem;
    }

    public StarImportJSONDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}
