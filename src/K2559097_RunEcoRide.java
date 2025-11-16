import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class K2559097_RunEcoRide {

    // Global Scanner and System Instance
    private static Scanner scanner = new Scanner(System.in);
    private static K2559097_EcoRideSystem system = new K2559097_EcoRideSystem();

    public static void main(String[] args) {
        // Preload some data so the system isn't empty
        initializeSystemData();

        boolean running = true;
        while (running) {
            System.out.println("\n=========================================");
            System.out.println("      EcoRide Car Rental System          ");
            System.out.println("=========================================");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleAdminLogin();
                    break;
                case "2":
                    handleCustomerMenu();
                    break;
                case "3":
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // --- ADMIN SECTION ---

    private static void handleAdminLogin() {
        System.out.print("\nEnter Admin Username: ");
        String user = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String pass = scanner.nextLine();

        // Hardcoded admin credentials
        if (user.equals("admin") && pass.equals("admin123")) {
            System.out.println("Login Successful!");
            runAdminMenu();
        } else {
            System.out.println("Invalid Credentials.");
        }
    }

    private static void runAdminMenu() {
        boolean inAdmin = true;
        while (inAdmin) {
            System.out.println("\n--- ADMIN DASHBOARD ---");
            System.out.println("1. Add New Vehicle");
            System.out.println("2. Update Vehicle Status");
            System.out.println("3. View All Vehicles");
            System.out.println("4. View All Bookings");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    adminAddVehicle();
                    break;
                case "2":
                    adminUpdateStatus();
                    break;
                case "3":
                    adminViewVehicles();
                    break;
                case "4":
                    adminViewBookings();
                    break;
                case "5":
                    inAdmin = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void adminAddVehicle() {
        System.out.println("\n--- Add New Vehicle ---");
        System.out.println("Select Type: 1.Compact 2.Hybrid 3.Electric 4.LuxurySUV");
        String typeChoice = scanner.nextLine();

        System.out.print("Enter Car ID: ");
        String carID = scanner.nextLine();
        System.out.print("Enter Model Name: ");
        String model = scanner.nextLine();

        K2559097_Vehicle v = null;
        switch (typeChoice) {
            case "1": v = new K2559097_CompactPetrolCar(carID, model); break;
            case "2": v = new K2559097_HybridCar(carID, model); break;
            case "3": v = new K2559097_ElectricCar(carID, model); break;
            case "4": v = new K2559097_LuxurySUV(carID, model); break;
            default: System.out.println("Invalid Type."); return;
        }

        system.addVehicle(v);
        System.out.println("Vehicle Added Successfully.");
    }

    private static void adminUpdateStatus() {
        System.out.print("\nEnter Car ID to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Status (Available/Reserved/Maintenance): ");
        String status = scanner.nextLine();
        system.updateVehicleStatus(id, status);
    }

    private static void adminViewVehicles() {
        System.out.println("\n--- Fleet Overview ---");
        for (K2559097_Vehicle v : system.getAllVehicles()) {
            System.out.printf("ID: %-8s | Model: %-15s | Category: %-15s | Status: %s%n",
                    v.getCarID(), v.getModel(), v.getCategory(), v.getAvailabilityStatus());
        }
    }

    private static void adminViewBookings() {
        System.out.println("\n--- All System Bookings ---");
        List<K2559097_Booking> list = system.getAllBookings();
        if (list.isEmpty()) System.out.println("No bookings found.");
        for (K2559097_Booking b : list) {
            System.out.println("Booking: " + b.getBookingID() + " | Car: " + b.getVehicle().getCarID() + " | Cust: " + b.getCustomer().getName());
        }
    }

    // --- CUSTOMER SECTION ---

    private static void handleCustomerMenu() {
        System.out.println("\n--- CUSTOMER AREA ---");
        System.out.println("1. I am a New Customer (Register)");
        System.out.println("2. I am an Existing Customer (Login)");
        System.out.print("Select: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            K2559097_Customer c = performRegistration();
            if (c != null) runCustomerDashboard(c);
        } else if (choice.equals("2")) {
            System.out.print("Enter your registered Email: ");
            String email = scanner.nextLine();
            K2559097_Customer c = system.findCustomerByEmail(email);
            if (c != null) {
                System.out.println("Welcome back, " + c.getName() + "!");
                runCustomerDashboard(c);
            } else {
                System.out.println("Email not found. Please register first.");
            }
        }
    }

    private static K2559097_Customer performRegistration() {
        System.out.println("\n--- Registration ---");
        System.out.print("NIC/Passport: ");
        String nic = scanner.nextLine();
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Contact No: ");
        String contact = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        return system.registerCustomer(nic, name, contact, email);
    }

    private static void runCustomerDashboard(K2559097_Customer currentCustomer) {
        boolean inCust = true;
        while (inCust) {
            System.out.println("\n--- DASHBOARD: " + currentCustomer.getName() + " ---");
            System.out.println("1. Browse Available Cars");
            System.out.println("2. Book a Car");
            System.out.println("3. My Bookings & Invoices");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    adminViewVehicles(); // Reuse admin view for simplicity
                    break;
                case "2":
                    performBooking(currentCustomer);
                    break;
                case "3":
                    viewMyHistory(currentCustomer);
                    break;
                case "4":
                    inCust = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void performBooking(K2559097_Customer customer) {
        System.out.println("\n--- Book a Vehicle ---");
        System.out.print("Enter Car ID you want to book: ");
        String carID = scanner.nextLine();

        System.out.println("Enter Rental Start Date (YYYY-MM-DD): ");
        LocalDate start = readDate();
        if (start == null) return;

        System.out.println("Enter Rental End Date (YYYY-MM-DD): ");
        LocalDate end = readDate();
        if (end == null) return;

        System.out.print("Expected Total Kilometers: ");
        int kms = -1;
        try {
            kms = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        K2559097_Booking b = system.bookCar(customer, carID, start, end, kms);
        if (b != null) {
            System.out.println("Would you like to generate your invoice now? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                K2559097_Invoice inv = system.generateInvoiceForBooking(b);
                inv.printInvoiceDetails();
            }
        }
    }

    private static void viewMyHistory(K2559097_Customer customer) {
        System.out.println("\n--- My Booking History ---");
        List<K2559097_Booking> myBookings = system.getBookingsForCustomer(customer);

        if (myBookings.isEmpty()) {
            System.out.println("You have no bookings yet.");
        } else {
            for (K2559097_Booking b : myBookings) {
                System.out.println("ID: " + b.getBookingID() + " | Car: " + b.getVehicle().getModel() + " | Dates: " + b.getRentalStartDate() + " to " + b.getRentalEndDate());
                // Option to reprint invoice
                System.out.println("   (To print invoice, enter booking ID, or press Enter to skip)");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase(b.getBookingID())) {
                    system.generateInvoiceForBooking(b).printInvoiceDetails();
                }
            }
        }
    }

    // --- HELPERS ---

    private static LocalDate readDate() {
        try {
            String d = scanner.nextLine();
            return LocalDate.parse(d);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date Format. Please use YYYY-MM-DD.");
            return null;
        }
    }

    private static void initializeSystemData() {
        // Add dummy data so admin doesn't see an empty system
        system.addVehicle(new K2559097_CompactPetrolCar("CP001", "Toyota Aqua"));
        system.addVehicle(new K2559097_HybridCar("HY001", "Toyota Prius"));
        system.addVehicle(new K2559097_ElectricCar("EL001", "Nissan Leaf"));
        system.addVehicle(new K2559097_LuxurySUV("SU001", "BMW X5"));

        // Add a dummy customer
        system.registerCustomer("123V", "Test User", "077123", "test@email.com");
    }
}