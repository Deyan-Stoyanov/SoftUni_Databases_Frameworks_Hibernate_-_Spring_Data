package hiberspring.dtos.imports;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownImportFromJsonDto {
    @Expose
    @NotNull
    private String name;
    @Expose
    @NotNull
    private Integer population;

    public TownImportFromJsonDto(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public TownImportFromJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
