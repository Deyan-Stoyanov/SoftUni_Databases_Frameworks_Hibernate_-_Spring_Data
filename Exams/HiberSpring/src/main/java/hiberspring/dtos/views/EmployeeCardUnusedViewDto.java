package hiberspring.dtos.views;

import com.google.gson.annotations.Expose;

public class EmployeeCardUnusedViewDto {
    @Expose
    private String number;

    public EmployeeCardUnusedViewDto(String number) {
        this.number = number;
    }

    public EmployeeCardUnusedViewDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
