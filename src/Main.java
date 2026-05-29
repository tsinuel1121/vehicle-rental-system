public class Main {

    public static void main(String[] args) {

        //  1. Create the agency
        RentalAgency agency = new RentalAgency("DriveEasy Rentals");

        // 2. Instantiate subclass objects (Inheritance — V3.0) 
        // Each constructor calls super() internally. Why? To initialize the Vehicle part of the object!
        Car car1  = new Car ("C001", "Toyota",  "Corolla",  2022, 15000, 40.0, 5,   true);
        Car car2  = new Car ("C002", "Honda",   "Civic",    2023,  8000, 45.0, 5,   false);
        Motorcycle moto1 = new Motorcycle("M001", "Yamaha",  "MT-07",    2021, 20000, 25.0, "Sport");
        Motorcycle moto2 = new Motorcycle("M002", "Harley",  "Sportster",2020, 35000, 30.0, "Cruiser");
        Truck truck1= new Truck ("T001", "Ford",    "F-250",    2021, 50000, 80.0, 1.5, false);
        Truck truck2= new Truck ("T002", "Volvo",   "FH16",     2019, 90000,120.0, 5.0, true);

        //  3. Add to fleet 
        System.out.println("\n  Adding vehicles to fleet...");
        agency.addVehicle(car1);
        agency.addVehicle(car2);
        agency.addVehicle(moto1);
        agency.addVehicle(moto2);
        agency.addVehicle(truck1);
        agency.addVehicle(truck2);

        //  4. Display full fleet — polymorphic displayInfo() calls 
        agency.displayAllVehicles();

        // ── 5. Cost estimates — polymorphic calculateRentalCost() calls ───────
        //    Vehicle reference calls the right subclass method automatically. 
        //why? Because calculateRentalCost() is overridden in each subclass, and Java resolves the method to call at runtime based on the actual object type (not the reference type).
        agency.showCostEstimates(3);

        //  6. METHOD OVERLOADING (compile-time polymorphism 
        System.out.println("\n                     ");
        System.out.println("  Rental Transactions");
        System.out.println("                       ");

        System.out.println("\n  [Car rental — no insurance]");
        car1.rent(3); // overloaded: rent(int)

        System.out.println("\n  [Car rental — WITH insurance]");
        car2.rent(5, true); // overloaded: rent(int, boolean)

        System.out.println("\n  [Motorcycle — weekend deal, no helmet]");
        moto1.rent(2); // triggers 10% discount

        System.out.println("\n  [Motorcycle — with helmet]");
        moto2.rent(4, true);

        System.out.println("\n  [Truck — standard]");
        truck1.rent(2);

        System.out.println("\n  [Truck — with GPS]");
        truck2.rent(3, true); // CDL warning fires here

        //  7. Available vehicles after rentals 
        agency.displayAvailableVehicles();

        //  8. Return a vehicle and log mileage 
        System.out.println("\n                      ");
        System.out.println("  Vehicle Return");
        System.out.println("                        ");
        agency.returnVehicle("C001", 320);
        agency.returnVehicle("M001", 185);

        //  9. Available vehicles after returns 
        agency.displayAvailableVehicles();

        //  10. SUPERCLASS REFERENCE (runtime polymorphism) 
        System.out.println("\n                      ");
        System.out.println("  Superclass Reference Demo");
        System.out.println("                        ");
        Vehicle[] mixed = { car1, moto2, truck1 };   // Vehicle refs, subclass objects
        for (Vehicle v : mixed) {
            System.out.printf("  %-6s → cost for 1 day: $%.2f  (type resolved at runtime)%n",
                    v.getVehicleId(), v.calculateRentalCost(1));
        }

        System.out.println("\n  Demo complete. Thank you!");
    }
}
