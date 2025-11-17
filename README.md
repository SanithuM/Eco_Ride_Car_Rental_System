# EcoRide Car Rental System

This project is a fully interactive, menu-driven console application for a car rental company, "EcoRide," as part of a coursework assignment.

The application is built using Object-Oriented Programming (OOP) principles and features a clear **Separation of Concerns**. All core business logic, rules, and data management are handled in the "logic layer" (e.g., `EcoRideSystem`). All user interaction, menus, and console I/O (using `java.util.Scanner`) are handled in the "presentation layer" (`RunEcoRide`).

## 🚗 Case Study
The system is based on the "EcoRide Car Rental System" case study. The company manages a fleet of eco-friendly vehicles and needs a system to handle customer registrations, vehicle bookings, and invoice generation based on a specific set of business rules.

## ✨ Core Features
The system successfully implements the following functionalities:

* **Admin Dashboard:** A password-protected portal (`admin`/`admin123`) to:
    * Add new vehicles to the fleet (Compact, Hybrid, Electric, SUV).
    * Update the status of existing vehicles (e.g., "Available", "Reserved", "Maintenance").
    * View a complete list of all vehicles in the fleet.
    * View all bookings in the system.
* **Customer Portal:** An interactive menu for:
    * **New Customer Registration:** Saves a new customer's details.
    * **Existing Customer Login:** "Logs in" a user by verifying their email address.
* **Customer Dashboard:** Once logged in, a customer can:
    * Browse all available cars.
    * Make a new booking.
    * View their personal booking history and generate invoices.
* **Booking System:**
    * Guides the user through an interactive booking process (selecting a car, dates, and mileage).
    * Enforces all business rules (e.g., vehicle availability, 3-day advance booking rule).
* **Detailed Invoice Generation:** Automatically calculates and prints a detailed invoice for any booking on demand.

## 🏗️ OOP Concepts Applied
This project heavily emphasizes core OOP and design principles:

* **Separation of Concerns:** A clear architectural split between the Presentation Layer (`RunEcoRide`) which handles user input, and the Logic Layer (`EcoRideSystem`) which handles business rules.
* **Inheritance & Abstraction:** An abstract `Vehicle` base class is extended by four concrete subclasses (`CompactPetrolCar`, `HybridCar`, `ElectricCar`, `LuxurySUV`).
* **Polymorphism:** The system uses the `Vehicle` base class to manage all vehicle types in one list, while dynamically calling the correct overridden methods (e.g., `getTaxRate()`) for fee calculations.
* **Encapsulation:** All class attributes are `private`, with access controlled by public methods. Business logic (like fee calculation) is encapsulated within its own class (`Invoice`).

## 🛠️ Tech Stack
* **Language:** Java
* **Core Packages:**
    * `java.util.ArrayList` (for in-memory data storage)
    * `java.util.Scanner` (for interactive user input)
    * `java.time.LocalDate` (for date management and calculations)

## 🚀 How to Run
This is an interactive application. You must compile and run the `RunEcoRide` class, which will launch the main menu.

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

4.  **Interact with the Application:**
    * The application will launch the main menu.

    * **To test Admin features:**
        1.  Select `1. Admin Login`.
        2.  Username: `admin`
        3.  Password: `admin123`
        4.  From the Admin Dashboard, you can add vehicles, update status, etc.

    * **To test Customer features:**
        1.  Select `2. Customer Menu`.
        2.  You can `1. Register` as a new user or `2. Login` with an existing email.
        3.  (A pre-loaded user `test@email.com` exists for quick testing).
        4.  From the Customer Dashboard, you can browse cars, make a booking, and generate an invoice.
