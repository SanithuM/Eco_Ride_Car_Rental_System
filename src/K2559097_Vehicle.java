public abstract class K2559097_Vehicle {

    // Attributes common to all vehicles
    private String carID;
    private String model;
    private String category;
    private String availabilityStatus; // Available, Reserved, Under Maintenance

    // Constructor to initialize a new vehicle
    public K2559097_Vehicle(String carID, String model, String category) {
        this.carID = carID;
        this.model = model;
        this.category = category;
        this.availabilityStatus = "Available"; // Default status
    }

    // --- Getters for vehicle details ---

    public String getCarID() {
        return carID;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    // --- Setter for updating status ---

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // --- Abstract Methods for Pricing ---

    /**
     * @return The daily rental fee for this vehicle category.
     */
    public abstract double getDailyRentalFee();

    /**
     * @return The number of free kilometers allowed per day.
     */
    public abstract int getFreeKmPerDay();

    /**
     * @return The charge for each kilometer driven over the free limit.
     */
    public abstract double getExtraKmCharge();

    /**
     * @return The tax rate (e.g., 0.10 for 10%) for this category.
     */
    public abstract double getTaxRate();
}