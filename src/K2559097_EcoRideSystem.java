import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class K2559097_EcoRideSystem {

    // Attributes
    private List<K2559097_Vehicle> vehicles;
    private List<K2559097_Customer> customers;
    private List<K2559097_Booking> bookings;

    // Constructor
    public K2559097_EcoRideSystem() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    // --- Vehicle Management Methods ---

    public void addVehicle(K2559097_Vehicle vehicle) {
        vehicles.add(vehicle);
        // System.out.println("Vehicle added: " + vehicle.getCarID()); // Removed print to keep logic clean
    }

    public void updateVehicleStatus(String carID, String newStatus) {
        K2559097_Vehicle vehicle = findVehicleByID(carID);
        if (vehicle != null) {
            vehicle.setAvailabilityStatus(newStatus);
        }
    }

    public boolean removeVehicle(String carID) {
        K2559097_Vehicle vehicle = findVehicleByID(carID);
        if (vehicle != null) {
            vehicles.remove(vehicle);
            return true;
        }
        return false;
    }

    public K2559097_Vehicle findVehicleByID(String carID) {
        for (K2559097_Vehicle v : vehicles) {
            if (v.getCarID().equalsIgnoreCase(carID)) {
                return v;
            }
        }
        return null;
    }

    public List<K2559097_Vehicle> getAllVehicles() {
        return vehicles;
    }

    // --- Customer Management Methods ---

    public K2559097_Customer registerCustomer(String nicOrPassport, String name, String contactNumber, String email) {
        K2559097_Customer newCustomer = new K2559097_Customer(nicOrPassport, name, contactNumber, email);
        customers.add(newCustomer);
        return newCustomer;
    }


    public K2559097_Customer findCustomerByEmail(String email) {
        for (K2559097_Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    // --- Booking Management Methods ---

    public K2559097_Booking bookCar(K2559097_Customer customer, String carID,
                                    LocalDate rentalStartDate, LocalDate rentalEndDate, int totalKilometers) {

        K2559097_Vehicle vehicle = findVehicleByID(carID);

        // Business Rule Checks
        if (vehicle == null) {
            System.out.println("Error: Vehicle not found.");
            return null;
        }
        if (!vehicle.getAvailabilityStatus().equalsIgnoreCase("Available")) {
            System.out.println("Error: Vehicle is " + vehicle.getAvailabilityStatus());
            return null;
        }
        long daysUntilRental = ChronoUnit.DAYS.between(LocalDate.now(), rentalStartDate);
        if (daysUntilRental < 3) {
            System.out.println("Error: Booking must be at least 3 days in advance.");
            return null;
        }

        // Create Booking
        K2559097_Booking newBooking = new K2559097_Booking(customer, vehicle, rentalStartDate, rentalEndDate, totalKilometers);
        bookings.add(newBooking);

        // Update Status
        updateVehicleStatus(carID, "Reserved");

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

    public List<K2559097_Booking> searchReservationByName(String customerName) {
        return bookings.stream()
                .filter(b -> b.getCustomer().getName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    /**
     * NEW METHOD: Finds all bookings for a specific customer object.
     */
    public List<K2559097_Booking> getBookingsForCustomer(K2559097_Customer customer) {
        return bookings.stream()
                .filter(b -> b.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public List<K2559097_Booking> getAllBookings() {
        return bookings;
    }

    // --- Invoice Generation ---

    public K2559097_Invoice generateInvoiceForBooking(K2559097_Booking booking) {
        if (booking != null) {
            return new K2559097_Invoice(booking);
        }
        return null;
    }
}