public class K2559097_ElectricCar extends K2559097_Vehicle {
    public K2559097_ElectricCar(String carID, String  model) {
        super(carID, model, "Electric");
    }

    // Implementation of Abstract Methods

    @Override
    public double getDailyRentalFee() {
        return 10000.0; // LKR 10,000
    }

    @Override
    public int getFreeKmPerDay() {
        return 200; // 200 Km
    }

    @Override
    public double getExtraKmCharge() {
        return 40.0; // LKR 40 per Km
    }

    @Override
    public double getTaxRate() {
        return 0.8; // 8%
    }
}
