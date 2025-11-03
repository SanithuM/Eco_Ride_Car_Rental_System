public class K2559097_LuxurySUV  extends K2559097_Vehicle{
    public K2559097_LuxurySUV(String carID, String model) {
        super(carID, model, "Luxury SUV");

    }

    // Implementation of Abstract Methods

    @Override
    public double getDailyRentalFee() {
        return 15000.0; // LKR 15,000
    }

    @Override
    public int getFreeKmPerDay() {
        return 250; // 250 Km
    }

    @Override
    public double getExtraKmCharge() {
        return 75.0; // LKR 75 per Km
    }

    @Override
    public double getTaxRate() {
        return 0.15; // 15%
    }
}
