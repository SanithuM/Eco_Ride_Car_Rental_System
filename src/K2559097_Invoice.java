import java.text.NumberFormat;
import java.util.Locale;

public class K2559097_Invoice {

    // Attributes
    private String invoiceID;
    private K2559097_Booking booking;

    // Helper for formatting currency
    private  static final NumberFormat currencyFormatter =  NumberFormat.getCurrencyInstance(new Locale("en","LK"));

    // Constructor
    public K2559097_Invoice(K2559097_Booking booking) {
        this.invoiceID = "INV-" + booking.getBookingID();
        this.booking = booking;
    }

    // Private Calculation Methods
    private double calculateBasePrice() {
        double dailyFee = booking.getVehicle().getDailyRentalFee();
        long numDays = booking.getNumberOfDays();
        return dailyFee * numDays;
    }

    private double calculateExtraKmCharge() {
        long numDays = booking.getNumberOfDays();
        int totalKmDriven = booking.getTotalKilometers();

        int freeKmPerDay = booking.getVehicle().getFreeKmPerDay();
        double extraKmCharge = booking.getVehicle().getExtraKmCharge();

        int totalFreeKm = (int) (freeKmPerDay * numDays);

        if (totalKmDriven > totalFreeKm) {
            int extraKms = totalKmDriven - totalFreeKm;
            return extraKmCharge * extraKms;
        }
        return 0.0;
    }

    private double calculateDiscount(double basePrice) {
        if (booking.getNumberOfDays() >= 7) {
            return basePrice * 0.10;
        }
        return 0.0;
    }

    private double calculateTax(double priceAfterDiscount) {
        double taxRate = booking.getVehicle().getTaxRate();
        return priceAfterDiscount * taxRate;
    }

    // Public Method for Final Calculation

    public double calculateFinalAmount() {
        // Step 1: Base Price
        double basePrice = calculateBasePrice();

        // Step 2: Extra Km Charges
        double extraKmCharge = calculateExtraKmCharge();

        // Step 3: Apply Discount
        double discount = calculateDiscount(basePrice);
        double priceAfterDiscount = basePrice - discount;

        // Step 4: Add Tax
        double tax = calculateTax(priceAfterDiscount);

        // Step 5: Deduct Deposit
        double deposit = booking.getDeposit();

        // Final Amount = (Price After Discount) + Extra Km + Tax - Deposit
        return (priceAfterDiscount + extraKmCharge + tax) - deposit;
    }

    // Public Method for Printing Invoice Details

    public void printInvoiceDetails() {
        K2559097_Customer customer = booking.getCustomer();
        K2559097_Vehicle vehicle = booking.getVehicle();

        // Calculation steps for transparency
        double basePrice = calculateBasePrice();
        double extraKmCharge = calculateExtraKmCharge();
        double discount = calculateDiscount(basePrice);
        double priceAfterDiscount = basePrice - discount;
        double tax = calculateTax(priceAfterDiscount);
        double deposit = booking.getDeposit();
        double finalAmount = calculateFinalAmount();

        System.out.println("=========================================");
        System.out.println("          EcoRide Car Rental             ");
        System.out.println("=========================================");
        System.out.println("INVOICE ID: " + this.invoiceID);
        System.out.println("Booking ID: " + booking.getBookingID());
        System.out.println("Date Issued: " + java.time.LocalDate.now());
        System.out.println("-----------------------------------------");
        System.out.println("CUSTOMER DETAILS:");
        System.out.println("  Name: " + customer.getName());
        System.out.println("  NIC/Passport: " + customer.getNicOrPassport());
        System.out.println("-----------------------------------------");
        System.out.println("CAR DETAILS:");
        System.out.println("  Car ID: " + vehicle.getCarID());
        System.out.println("  Model: " + vehicle.getModel());
        System.out.println("  Category: " + vehicle.getCategory());
        System.out.println("  Daily Rate: " + currencyFormatter.format(vehicle.getDailyRentalFee()));
        System.out.println("-----------------------------------------");
        System.out.println("RENTAL DETAILS:");
        System.out.println("  Start Date: " + booking.getRentalStartDate());
        System.out.println("  End Date: " + booking.getRentalEndDate());
        System.out.println("  Rental Duration: " + booking.getNumberOfDays() + " days");
        System.out.println("  Mileage Used: " + booking.getTotalKilometers() + " km");
        System.out.println("-----------------------------------------");
        System.out.println("FEE CALCULATION:");
        System.out.println("  Base Rental Price: " + currencyFormatter.format(basePrice));
        System.out.println("  Extra Mileage Charge: " + currencyFormatter.format(extraKmCharge));
        System.out.println("  Discount (7+ days): " + currencyFormatter.format(discount));
        System.out.println("  Subtotal: " + currencyFormatter.format(priceAfterDiscount + extraKmCharge));
        System.out.println("  Tax (" + (vehicle.getTaxRate() * 100) + "%): " + currencyFormatter.format(tax));
        System.out.println("  Total Before Deposit: " + currencyFormatter.format(priceAfterDiscount + extraKmCharge + tax));
        System.out.println("  Deposit Handled (Deducted): " + currencyFormatter.format(deposit));
        System.out.println("=========================================");
        System.out.println("  FINAL PAYABLE AMOUNT: " + currencyFormatter.format(finalAmount));
        System.out.println("=========================================");
    }

}
