package org.softuni.mostwanted.model.dto.exportDtos.json;

import com.google.gson.annotations.Expose;

public class TownsWithRacersJSONExport {
    @Expose
    private String name;
    @Expose
    private Long racers;

    public TownsWithRacersJSONExport(String name, Long racers) {
        this.name = name;
        this.racers = racers;
    }

    public TownsWithRacersJSONExport() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRacers() {
        return racers;
    }

    public void setRacers(Long racers) {
        this.racers = racers;
    }
}
