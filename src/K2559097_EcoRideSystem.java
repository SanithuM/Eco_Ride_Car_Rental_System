import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class K2559097_EcoRideSystem {

    // --- Attributes ---
    // Using ArrayLists as an in-memory database
    private List<K2559097_Vehicle> vehicles;
    private List<K2559097_Customer> customers;
    private List<K2559097_Booking> bookings;

    // --- Constructor ---
    public K2559097_EcoRideSystem() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    // --- Vehicle Management Methods ---


    public void addVehicle(K2559097_Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle added: " + vehicle.getCarID() + " - " + vehicle.getModel());
    }


    public void updateVehicleStatus(String carID, String newStatus) {
        K2559097_Vehicle vehicle = findVehicleByID(carID);
        if (vehicle != null) {
            vehicle.setAvailabilityStatus(newStatus);
            System.out.println("Vehicle " + carID + " status updated to: " + newStatus);
        } else {
            System.out.println("Error: Vehicle " + carID + " not found.");
        }
    }


    public void removeVehicle(String carID) {
        K2559097_Vehicle vehicle = findVehicleByID(carID);
        if (vehicle != null) {
            vehicles.remove(vehicle);
            System.out.println("Vehicle removed: " + carID);
        } else {
            System.out.println("Error: Vehicle " + carID + " not found.");
        }
    }


    public K2559097_Vehicle findVehicleByID(String carID) {
        for (K2559097_Vehicle v : vehicles) {
            if (v.getCarID().equalsIgnoreCase(carID)) {
                return v;
            }
        }
        return null;
    }

    // --- Customer Management Methods ---


    public K2559097_Customer registerCustomer(String nicOrPassport, String name, String contactNumber, String email) {
        K2559097_Customer newCustomer = new K2559097_Customer(nicOrPassport, name, contactNumber, email);
        customers.add(newCustomer);
        System.out.println("Customer registered: " + newCustomer.getName());
        return newCustomer;
    }

    // --- Booking Management Methods ---


    public K2559097_Booking bookCar(K2559097_Customer customer, String carID,
                                        LocalDate rentalStartDate, LocalDate rentalEndDate, int totalKilometers) {

        K2559097_Vehicle vehicle = findVehicleByID(carID);

        // --- Business Rule Checks ---
        if (vehicle == null) {
            System.out.println("Booking Failed: Vehicle " + carID + " not found.");
            return null;
        }

        // Rule: Check availability status
        if (!vehicle.getAvailabilityStatus().equalsIgnoreCase("Available")) {
            System.out.println("Booking Failed: Vehicle " + carID + " is not available. Status: " + vehicle.getAvailabilityStatus());
            return null;
        }

        // Rule: Schedule at least 3 days prior
        long daysUntilRental = ChronoUnit.DAYS.between(LocalDate.now(), rentalStartDate);
        if (daysUntilRental < 3) {
            System.out.println("Booking Failed: Must book at least 3 days in advance. Days until rental: " + daysUntilRental);
            return null;
        }


        // All checks passed, create the booking
        K2559097_Booking newBooking = new K2559097_Booking(customer, vehicle, rentalStartDate, rentalEndDate, totalKilometers);
        bookings.add(newBooking);

        // Update the car's status to "Reserved"
        updateVehicleStatus(carID, "Reserved");

        System.out.println("Booking Successful! ID: " + newBooking.getBookingID() + " for " + customer.getName());
        return newBooking;
    }


    public K2559097_Booking searchReservationByID(String bookingID) {
        for (K2559097_Booking b : bookings) {
            if (b.getBookingID().equalsIgnoreCase(bookingID)) {
                return b;
            }
        }
        return null;
    }


    // Searches for all reservations made by a customer

    public List<K2559097_Booking> searchReservationByName(String customerName) {

        return bookings.stream()
                .filter(b -> b.getCustomer().getName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    // Finds all bookings that are active on a specific date.

    public List<K2559097_Booking> viewBookingsByDate(LocalDate date) {
        return bookings.stream()
                .filter(b -> !(date.isBefore(b.getRentalStartDate()) || date.isAfter(b.getRentalEndDate())))
                .collect(Collectors.toList());
    }

    // --- Invoice Generation Method ---

    public K2559097_Invoice generateInvoiceForBooking(K2559097_Booking booking) {
        if (booking == null) {
            System.out.println("Invoice Generation Failed: Booking is null.");
            return null;
        }
        return new K2559097_Invoice(booking);
    }

    // --- Getters for the lists ---

    public List<K2559097_Vehicle> getAllVehicles() {
        return vehicles;
    }
}