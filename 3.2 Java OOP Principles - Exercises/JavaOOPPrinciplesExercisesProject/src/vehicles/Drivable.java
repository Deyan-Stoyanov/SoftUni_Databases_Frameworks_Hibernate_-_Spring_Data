package vehicles;

    public interface Drivable {
        void drive(double distance);
        void refuel(double liters);
        double getFuelQuantity();
        double getFuelConsumption();
}
