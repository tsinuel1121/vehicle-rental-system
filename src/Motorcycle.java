
public class Motorcycle extends Vehicle {

    //  Motorcycle-specific attribute 
    private String type;   // e.g., "Sport", "Cruiser", "Off-road"

    //  Constructor ( calls super) 
    public Motorcycle(String vehicleId, String brand, String model,
                      int year, double mileage, double baseDailyRate,
                      String type) {
        super(vehicleId, brand, model, year, mileage, baseDailyRate);
        this.type = type;
    }

    //  Method OVERRIDING (V4.0 — runtime polymorphism) 
    @Override
    public double calculateRentalCost(int days) {
        double cost = getBaseDailyRate() * days;
        if (days <= 2) cost *= 0.90;   // 10% weekend discount
        return cost;
    }

    //  Method OVERLOADING (V4.0 — compile-time polymorphism) 
    public double rent(int days) {
        return rent(days, false);
    }

    public double rent(int days, boolean withHelmet) {
        if (!isAvailable()) {
            System.out.println("  [!] " + getBrand() + " " + getModel() + " is not available.");
            return 0;
        }
        setAvailable(false);
        double total = calculateRentalCost(days);
        if (withHelmet) total += 3.0 * days;
        System.out.println("  Motorcycle rented for " + days + " day(s)."
                + (withHelmet ? " Helmet included." : ""));
        System.out.println("  Total cost: $" + String.format("%.2f", total));
        return total;
    }

    //  Override displayInfo() to add Motorcycle-specific details
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Type    : Motorcycle (" + type + ")");
        System.out.println("                  ");
    }

    //  Getter 
    public String getType() { return type; }
}
