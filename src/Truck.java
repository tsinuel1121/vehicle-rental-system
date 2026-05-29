public class Truck extends Vehicle {

    //  Truck-specific attributes 
    private double payloadCapacityTons; // how much cargo it can carry
    private boolean requiresCDL;   // Commercial Driver's License needed?

    //  Constructor (V3.0) 
    public Truck(String vehicleId, String brand, String model,
                 int year, double mileage, double baseDailyRate,
                 double payloadCapacityTons, boolean requiresCDL) {
        super(vehicleId, brand, model, year, mileage, baseDailyRate);
        this.payloadCapacityTons = payloadCapacityTons;
        this.requiresCDL         = requiresCDL;
    }

    //  Method OVERRIDING (V4.0 — runtime polymorphism) 
    @Override
    public double calculateRentalCost(int days) {
        double loadSurcharge = payloadCapacityTons * 15.0 * days;
        return (getBaseDailyRate() * days) + loadSurcharge;
    }

    //  Method OVERLOADING ( compile-time polymorphism) 
    // Overloaded rent() method: one with GPS option, one without
    public double rent(int days) {
        return rent(days, false);
    }

    public double rent(int days, boolean withGPS) {
        if (!isAvailable()) {
            System.out.println("  [!] " + getBrand() + " " + getModel() + " is not available.");
            return 0;
        }
        if (requiresCDL) {
            System.out.println("  [!] Note: This truck requires a CDL licence.");
        }
        setAvailable(false);
        double total = calculateRentalCost(days);
        if (withGPS) total += 8.0 * days;
        System.out.println("  Truck rented for " + days + " day(s)."
                + (withGPS ? " GPS included." : ""));
        System.out.println("  Total cost: $" + String.format("%.2f", total));
        return total;
    }

    //  Override displayInfo() to add Truck-specific details
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Type    : Truck");
        System.out.println("  Payload : " + payloadCapacityTons + " tons");
        System.out.println("  CDL req.: " + (requiresCDL ? "Yes" : "No"));
        System.out.println("                        ");
    }

    //  Getters 
    public double getPayloadCapacityTons() { return payloadCapacityTons; }
    public boolean requiresCDL()           { return requiresCDL; }
}
