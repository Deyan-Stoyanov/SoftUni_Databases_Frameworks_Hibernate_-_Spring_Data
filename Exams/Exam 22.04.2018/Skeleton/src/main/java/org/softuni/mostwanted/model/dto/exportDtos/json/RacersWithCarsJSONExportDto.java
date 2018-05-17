package org.softuni.mostwanted.model.dto.exportDtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.util.List;
public class RacersWithCarsJSONExportDto {

    @Expose
    private String name;

    @Expose
    @NotNull
    private Integer age;

    @Expose
    @SerializedName(value = "cars")
    private List<String> cars;

    public RacersWithCarsJSONExportDto(String name, Integer age, List<String> cars) {
        this.name = name;
        this.age = age;
        this.cars = cars;
    }

    public RacersWithCarsJSONExportDto() {
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

    public List<String> getCars() {
        return cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }
}
