# DriveEasy — Vehicle Rental System MVP
### Java OOP Demonstration | V1.0 – V4.0

A console-based vehicle rental management system that demonstrates all four
core OOP pillars in Java through a practical, real-world scenario.

---

## Project Structure

```
VehicleRental/
└── src/
    ├── Vehicle.java        ← Abstract base class (superclass)
    ├── Car.java            ← Subclass of Vehicle
    ├── Motorcycle.java     ← Subclass of Vehicle
    ├── Truck.java          ← Subclass of Vehicle
    ├── RentalAgency.java   ← Fleet manager class
    └── Main.java           ← Entry point / demo runner
```

---

## How to Compile & Run

```bash
# 1. Navigate to the src folder
cd VehicleRental/src

# 2. Compile all files
javac *.java

# 3. Run the application
java Main
```

---

## OOP Concept Map (Where Each Principle Appears)

### ✅ V1.0 & V2.0 — Classes, Objects & Encapsulation

| Concept | File | Lines |
|---|---|---|
| Class definition with attributes | `Vehicle.java` | 14–23 |
| Object instantiation | `Main.java` | 18–23 |
| `private` fields | `Vehicle.java` | 14–21 |
| Getters & setters with validation | `Vehicle.java` | 53–75 |
| `protected`/`public` access | `Vehicle.java`, `Car.java` | throughout |

### ✅ V3.0 — Inheritance

| Concept | File | Lines |
|---|---|---|
| `extends` keyword | `Car.java` | 7 |
| `super()` constructor call | `Car.java` | 17 |
| Inherited method reuse | `Car.java` → `displayInfo()` | 50 |
| "is-a" relationship | `Car`, `Motorcycle`, `Truck` all extend `Vehicle` | — |

### ✅ V4.0 — Polymorphism

| Concept | File | Lines |
|---|---|---|
| Method **overriding** (runtime) | `Car.java` `calculateRentalCost()` | 25–30 |
| Method **overriding** (runtime) | `Motorcycle.java` `calculateRentalCost()` | 27–32 |
| Method **overriding** (runtime) | `Truck.java` `calculateRentalCost()` | 29–33 |
| Method **overloading** (compile-time) | `Car.java` `rent()` / `rent(,)` | 37–52 |
| Method **overloading** (compile-time) | `Motorcycle.java` `rent()` / `rent(,)` | 39–54 |
| Method **overloading** (compile-time) | `Truck.java` `rent()` / `rent(,)` | 41–57 |
| **Superclass reference** → subclass object | `RentalAgency.java` `showCostEstimates()` | 67–74 |
| **Superclass reference** array | `Main.java` | 87–91 |

---

## Feature Walkthrough

1. **Fleet Setup** — Cars, Motorcycles, and Trucks are added to the agency.
2. **Display Fleet** — Polymorphic `displayInfo()` prints type-specific details per vehicle.
3. **Cost Estimates** — `calculateRentalCost()` called on `Vehicle` references; the JVM dispatches to the correct subclass implementation automatically.
4. **Rentals with Overloading** — Each vehicle type has `rent(days)` and `rent(days, extra)`, demonstrating compile-time polymorphism.
5. **Returns** — Vehicles are marked available again and mileage is updated via validated setter.
6. **Superclass Reference Demo** — A `Vehicle[]` array holds mixed subclass objects; calling `calculateRentalCost(1)` on each proves runtime dispatch.

---

## Design Decisions

- `Vehicle` is `abstract` because you never rent a generic "vehicle" — you rent a Car, Motorcycle, or Truck specifically. This enforces correct modelling.
- `calculateRentalCost()` is `abstract` in `Vehicle` so no subclass can forget to implement pricing.
- Validation in setters (`setMileage`, `setBaseDailyRate`) shows encapsulation doing real work, not just hiding fields behind boilerplate.
