package vehicles;

import java.text.DecimalFormat;

public class Truck implements Drivable{
    private static final double FUEL_CONSUMPTION_INCREASE = 1.6;
    private static final double REFUEL_PERCENTAGE = 0.95;
    private static final DecimalFormat dec = new DecimalFormat(".00");
    private double fuelQuantity;
    private double fuelConsumption;

    public Truck(double fuelQuantity, double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }

    public static double getRefuelPercentage() {
        return REFUEL_PERCENTAGE;
    }

    public static double getFuelConsumptionIncrease() {
        return FUEL_CONSUMPTION_INCREASE;
    }

    @Override
    public void drive(double distance) {
        if((distance * getFuelConsumption()) > this.getFuelQuantity()){
            System.out.println("Truck needs refueling");
        } else {
            System.out.printf("Truck travelled %.2f km%n", distance);
            this.setFuelQuantity(getFuelQuantity() - (distance * getFuelConsumption()));
        }
    }

    @Override
    public void refuel(double liters) {
        this.fuelQuantity += (liters * REFUEL_PERCENTAGE);
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
        return String.format("Truck: %.2f", getFuelQuantity());
    }
}
