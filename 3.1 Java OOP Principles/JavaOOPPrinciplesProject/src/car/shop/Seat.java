package car.shop;

public class Seat implements Car {

    private String countryProduced;

    public Seat() {
    }

    public Seat(String leon, String gray, int i, String spain) {

    }

    public String getCountryProduced() {
        return countryProduced;
    }

    public void setCountryProduced(String countryProduced) {
        this.countryProduced = countryProduced;
    }

    @Override
    public String toString() {
        return "Seat{}";
    }

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public int getHorsePower() {
        return 0;
    }
}
