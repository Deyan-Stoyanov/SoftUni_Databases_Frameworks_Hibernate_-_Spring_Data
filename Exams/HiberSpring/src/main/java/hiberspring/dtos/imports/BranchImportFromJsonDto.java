package hiberspring.dtos.imports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class BranchImportFromJsonDto {

    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private String town;

    public BranchImportFromJsonDto(String name, String town) {
        this.name = name;
        this.town = town;
    }

    public BranchImportFromJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
