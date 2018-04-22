package hiberspring.dtos.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeProductiveViewDto {
    @Expose
    @SerializedName(value = "full-name")
    private String fullName;
    @Expose
    private String position;
    @Expose
    private String number;

    public EmployeeProductiveViewDto(String fullName, String position, String number) {
        this.fullName = fullName;
        this.position = position;
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
