# EcoRide Car Rental System

This project is a Java-based backend simulation for a car rental company, "EcoRide," as part of a coursework.

The application is built using Object-Oriented Programming (OOP) principles to manage vehicles, customers, bookings, and invoices. It is designed as a simple console-based application without a database or user-input scanner, focusing purely on demonstrating OOP concepts and business logic.


## 🚗 Case Study
The system is based on the "EcoRide Car Rental System" case study. The company manages a fleet of eco-friendly vehicles and needs a system to handle customer registrations, vehicle bookings, and invoice generation based on a specific set of business rules.

## ✨ Core Features
The system successfully implements the following functionalities:

* **Vehicle Management:** Ability to add, update, and remove vehicle records from the fleet.
* **Customer Registration:** A simple class to register and store customer details.
* **Booking System:**
    * Allows customers to book available cars.
    * Enforces business rules, such as checking vehicle availability and requiring bookings to be made at least 3 days in advance.
* **Search Functionality:** Capable of searching for reservations by customer name or booking ID.
* **Detailed Invoice Generation:** Automatically calculates and generates a detailed invoice for a booking, including:
    * Base Price
    * Extra Mileage Charges
    * 7-Day+ Discount (10%)
    * Category-specific Tax
    * Deposit Handling

## 🏗️ OOP Concepts Applied
This project heavily emphasizes OOP principles:

* **Inheritance:** An abstract `Vehicle` base class is extended by four concrete subclasses (`CompactPetrolCar`, `HybridCar`, `ElectricCar`, `LuxurySUV`).
* **Polymorphism:** The `Vehicle` subclasses implement abstract methods (e.g., `getDailyRentalFee()`, `getTaxRate()`) to provide their own specific pricing rules from the case study's table.
* **Encapsulation:** All class attributes are `private`, with access provided through public `get` and `set` methods.
* **Abstraction:** The `Vehicle` class is `abstract`, hiding common implementation details and forcing subclasses to provide their own.

## 🛠️ Tech Stack
* **Language:** Java
* **Core Packages:** `java.time.LocalDate` (for date management) and `java.util.ArrayList` (for in-memory data storage).

# 🚀 How to Run
This program does not use a database or require user input (like `Scanner`). It is designed as a simulation that runs pre-defined test cases.

1.  **Ensure all `.java` files are in the same directory.** The key classes are:
    * `Vehicle.java`
    * `CompactPetrolCar.java`
    * `HybridCar.java`
    * `ElectricCar.java`
    * `LuxurySUV.java`
    * `Customer.java`
    * `Booking.java`
    * `Invoice.java`
    * `EcoRideSystem.java`
    * `RunEcoRide.java`

2.  **Compile all the Java files.**
    ```bash
    javac *.java
    ```

3.  **Run the `RunEcoRide` class.** This is the main entry point.
    ```bash
    java RunEcoRide
    ```

4.  **Check the Console Output.** The `RunEcoRide` class will automatically:
    * Create an `EcoRideSystem` instance.
    * Add vehicles to the fleet.
    * Register customers.
    * Simulate several booking scenarios (including successful and failed tests).
    * Generate and print a detailed invoice for a test booking, showing all the final calculations.
