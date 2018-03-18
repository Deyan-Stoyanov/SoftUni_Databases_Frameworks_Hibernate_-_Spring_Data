package ferrari;

public class Ferrari implements Drivable {
    private final String MODEL = "488-Spider";
    private String driverName;

    public Ferrari(String driverName) {
        this.driverName = driverName;
    }

    public String getMODEL() {
        return MODEL;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String brake() {
        return ("Brakes!");
    }

    @Override
    public String gas() {
        return ("Zadu6avam sA!");
    }
}
