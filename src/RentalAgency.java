import java.util.ArrayList;

public class RentalAgency {

    //  Encapsulated fleet list
    private String agencyName;
    private ArrayList<Vehicle> fleet;   // Fleet holds Vehicle references — key to polymorphism

    //  Constructor 
    public RentalAgency(String agencyName) {
        this.agencyName = agencyName;
        this.fleet      = new ArrayList<>();
    }

    //  Add a vehicle to the fleet 
    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
        System.out.println("  [+] Added: " + vehicle.getBrand()
                + " " + vehicle.getModel() + " (" + vehicle.getVehicleId() + ")");
    }

    //  Display all vehicles (uses polymorphic displayInfo()) 
    public void displayAllVehicles() {
        System.out.println("\n                      ");
        System.out.println("  " + agencyName + " — Full Fleet");
        System.out.println("                      ");
        for (Vehicle v : fleet) {
            v.displayInfo(); // polymorphic dispatch
        }
    }

    //  Display only available vehicles 
    public void displayAvailableVehicles() {
        System.out.println("\n                      ");
        System.out.println("  Available Vehicles");
        System.out.println("                        ");
        boolean found = false;
        for (Vehicle v : fleet) {
            if (v.isAvailable()) {
                v.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("  No vehicles currently available.");
    }

    //  Find vehicle by ID 
    public Vehicle findVehicleById(String id) {
        for (Vehicle v : fleet) {
            if (v.getVehicleId().equalsIgnoreCase(id)) return v;
        }
        System.out.println("  [!] Vehicle ID '" + id + "' not found.");
        return null;
    }

    //  Return a vehicle (mark it available again) and log mileage
    public void returnVehicle(String vehicleId, double kmDriven) {
        Vehicle v = findVehicleById(vehicleId);
        if (v != null && !v.isAvailable()) {
            v.setAvailable(true);
            v.setMileage(v.getMileage() + kmDriven);
            System.out.println("  Vehicle " + vehicleId + " returned. +"
                    + kmDriven + " km logged.");
        } else if (v != null) {
            System.out.println("  [!] Vehicle " + vehicleId + " wasn't rented.");
        }
    }

    // Show cost estimates for all available vehicles for a given rental duration
    // Polymorphic calls to calculateRentalCost() determine the correct cost based on vehicle type.
    public void showCostEstimates(int days) {
        System.out.println("\n                  ");
        System.out.println("  Cost Estimates for " + days + " Day(s)");
        System.out.println("                    ");
        for (Vehicle v : fleet) {
            if (v.isAvailable()) {
                System.out.printf("  %-6s | %-20s | $%.2f%n",
                        v.getVehicleId(),
                        v.getBrand() + " " + v.getModel(),
                        v.calculateRentalCost(days));   // ← polymorphic call
            }
        }
        System.out.println("                        ");
    }

    //  Getter 
    public String getAgencyName() { return agencyName; }
}
