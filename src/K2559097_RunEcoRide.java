import java.time.LocalDate;

public class K2559097_RunEcoRide {

    public static void main(String[] args) {

        // 1. System Setup
        System.out.println("Starting EcoRide System...");

        K2559097_EcoRideSystem system = new K2559097_EcoRideSystem();
        System.out.println("-----------------------------------------\n");

        // 2. Add Vehicles to Fleet
        System.out.println("--- Adding Vehicles ---");
        system.addVehicle(new K2559097_CompactPetrolCar("CP001", "Toyota Aqua"));
        system.addVehicle(new K2559097_HybridCar("HY001", "Toyota Prius"));
        system.addVehicle(new K2559097_ElectricCar("EL001", "Nissan Leaf"));
        system.addVehicle(new K2559097_LuxurySUV("SU001", "BMW X5"));
        System.out.println("\n--- Vehicle Fleet ---");
        for (K2559097_Vehicle v : system.getAllVehicles()) {
            System.out.println("  > " + v.getCarID() + " | " + v.getModel() + " | Status: " + v.getAvailabilityStatus());
        }
        System.out.println("\n-----------------------------------------\n");

        // 3. Register Customers
        System.out.println("--- Registering Customers ---");
        K2559097_Customer customer1 = system.registerCustomer("199012345V", "John Doe", "0771234567", "john.doe@email.com");
        K2559097_Customer customer2 = system.registerCustomer("198554321K", "Jane Smith", "0719876543", "jane.smith@email.com");
        System.out.println("\n-----------------------------------------\n");

        // 4. Simulate Bookings (Test Cases)
        System.out.println("--- Simulating Bookings ---");

        // Get dates for booking (must be 3+ days in advance)
        LocalDate today = LocalDate.now();

        // SCENARIO 1: Successful Booking (Compact Car, 5 days, within free km)
        System.out.println("\n[Scenario 1: Standard Booking]");
        LocalDate startDate1 = today.plusDays(10);
        LocalDate endDate1 = today.plusDays(15); // 5-day rental
        K2559097_Booking booking1 = system.bookCar(customer1, "CP001", startDate1, endDate1, 450); // 450km (within 5x100=500km limit)


        // SCENARIO 2: Successful Booking (SUV, 7 days, extra km, 10% discount)
        System.out.println("\n[Scenario 2: Long Rental (7 days) with Discount & Extra Km]");
        LocalDate startDate2 = today.plusDays(20);
        LocalDate endDate2 = today.plusDays(27); // 7-day rental
        K2559097_Booking booking2 = system.bookCar(customer2, "SU001", startDate2, endDate2, 2000); // 2000km (over 7x250=1750km limit)


        // SCENARIO 3: Failed Booking (Car not available)
        System.out.println("\n[Scenario 3: Failed Booking - Car Already Reserved]");
        // Try to book the same compact car (CP001) which is now "Reserved"
        system.bookCar(customer2, "CP001", startDate1, endDate1, 100);


        // SCENARIO 4: Failed Booking (Not 3 days in advance)
        System.out.println("\n[Scenario 4: Failed Booking - Not 3 Days in Advance]");
        LocalDate startDate4 = today.plusDays(1); // Only 1 day in advance
        LocalDate endDate4 = today.plusDays(3);
        system.bookCar(customer1, "HY001", startDate4, endDate4, 200);

        System.out.println("\n-----------------------------------------\n");

        // 5. Generate and Print Invoice
        System.out.println("--- Generating Invoice for Booking 2 ---");

        if (booking2 != null) {
            K2559097_Invoice invoice = system.generateInvoiceForBooking(booking2);
            invoice.printInvoiceDetails(); // This prints the full, detailed invoice
        } else {
            System.out.println("Could not generate invoice because Booking 2 failed.");
        }

        System.out.println("\n-----------------------------------------\n");
        System.out.println("EcoRide System Test Complete.");
    }
}