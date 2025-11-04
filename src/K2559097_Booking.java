import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class K2559097_Booking {

    // --- Attributes ---
    private String bookingID;
    private K2559097_Customer customer;
    private K2559097_Vehicle vehicle;
    private LocalDate bookingDate;      // When the booking was made
    private LocalDate rentalStartDate;  // When the rental period begins
    private LocalDate rentalEndDate;    // When the rental period ends
    private int totalKilometers;      // Planned total kilometers
    private double deposit;           // The refundable deposit 

    // --- Constructor ---

    public K2559097_Booking(K2559097_Customer customer, K2559097_Vehicle vehicle,
                                LocalDate rentalStartDate, LocalDate rentalEndDate, int totalKilometers) {

        // Generate a simple, unique booking ID
        this.bookingID = "B-" + System.currentTimeMillis();

        this.customer = customer;
        this.vehicle = vehicle;
        this.bookingDate = LocalDate.now(); // Set booking date to today
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.totalKilometers = totalKilometers;
        this.deposit = 5000.0; // Set the standard refundable deposit 
    }

    // Helper Methods

    public long getNumberOfDays() {
        // If start and end dates are the same, it's a 1-day rental.
        if (rentalStartDate.equals(rentalEndDate)) {
            return 1;
        }
        // Calculates the difference in days
        return ChronoUnit.DAYS.between(rentalStartDate, rentalEndDate);
    }


    public boolean isCancellable() {
        LocalDate twoDaysAfterBooking = this.bookingDate.plusDays(2);
        // If "today" is *after* two days from booking, it's not cancellable.
        return !LocalDate.now().isAfter(twoDaysAfterBooking);
    }

    // Getters

    public String getBookingID() {
        return bookingID;
    }

    public K2559097_Customer getCustomer() {
        return customer;
    }

    public K2559097_Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public int getTotalKilometers() {
        return totalKilometers;
    }

    public double getDeposit() {
        return deposit;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }
}