package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import java.io.Serializable;

public class SolarSystemImportJSONDto implements Serializable {
    @Expose
    @Column(nullable = false)
    private String name;

    public SolarSystemImportJSONDto(String name) {
        this.name = name;
    }

    public SolarSystemImportJSONDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

