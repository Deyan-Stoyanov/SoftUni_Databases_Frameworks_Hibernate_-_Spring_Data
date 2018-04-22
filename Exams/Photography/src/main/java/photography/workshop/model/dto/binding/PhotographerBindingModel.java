package photography.workshop.model.dto.binding;

import com.google.gson.annotations.Expose;

public class PhotographerBindingModel {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String phone;
    @Expose
    private Long[] lenses;

    public PhotographerBindingModel(String firstName, String lastName, String phone, Long[] lenses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.lenses = lenses;
    }

    public PhotographerBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long[] getLenses() {
        return lenses;
    }

    public void setLenses(Long[] lenses) {
        this.lenses = lenses;
    }
}
