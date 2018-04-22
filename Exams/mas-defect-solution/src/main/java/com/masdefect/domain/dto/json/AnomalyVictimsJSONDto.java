package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AnomalyVictimsJSONDto implements Serializable {
    @Expose
    @NotNull
    private Integer id;
    @Expose
    @NotNull
    private String person;

    public AnomalyVictimsJSONDto(Integer id, String person) {
        this.id = id;
        this.person = person;
    }

    public AnomalyVictimsJSONDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
