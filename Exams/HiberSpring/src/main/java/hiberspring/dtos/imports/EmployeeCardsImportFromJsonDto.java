package hiberspring.dtos.imports;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class EmployeeCardsImportFromJsonDto {
    @Expose
    @NotNull
    private String number;

    public EmployeeCardsImportFromJsonDto(String number) {
        this.number = number;
    }

    public EmployeeCardsImportFromJsonDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
