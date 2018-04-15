package app.domain.dto.json;

import com.google.gson.annotations.Expose;

public class PhoneNumberDto {
    @Expose
    private String number;

    public PhoneNumberDto(String number) {
        this.number = number;
    }

    public PhoneNumberDto() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return this.getNumber();
    }
}
