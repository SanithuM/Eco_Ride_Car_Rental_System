public class K2559097_HybridCar extends K2559097_Vehicle {
    public K2559097_HybridCar(String carID, String model) {
        super(carID, model, "Hybrid");
    }

    // Implementation of Abstract Methods

    @Override
    public double getDailyRentalFee() {
        return 7500.0; //LKR 7,500
    }

    @Override
    public int getFreeKmPerDay() {
        return 150; // 150 Km
    }

    @Override
    public double getExtraKmCharge() {
        return 60.0; // LKR 60 per Km
    }

    @Override
    public double getTaxRate() {
        return 0.12; // 12%
    }


}
