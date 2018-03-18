package vehicles;

import java.text.DecimalFormat;

public class Car implements Drivable{
    private static final double FUEL_CONSUMPTION_INCREASE = 0.9;
    private static final DecimalFormat dec = new DecimalFormat(".00");
    private double fuelQuantity;
    private double fuelConsumption;

    public Car(double fuelQuantity, double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }

    public static double getFuelConsumptionIncrease() {
        return FUEL_CONSUMPTION_INCREASE;
    }

    @Override
    public void drive(double distance) {
        if((distance * this.getFuelConsumption()) > this.getFuelQuantity()){
            System.out.println("Car needs refueling");
        } else {
            System.out.printf("Car travelled %.2f km%n", distance);
            this.setFuelQuantity(getFuelQuantity() - (distance * getFuelConsumption()));
        }
    }

    @Override
    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    @Override
    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    @Override
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption + FUEL_CONSUMPTION_INCREASE;
    }

    @Override
    public String toString() {
        return String.format("Car: %.2f", getFuelQuantity());
    }
}
