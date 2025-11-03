public class K2559097_CompactPetrolCar extends K2559097_Vehicle {

    //Constructor that calls the parent (Vehicle) constructor
    public K2559097_CompactPetrolCar(String carID, String model) {
        // Pass "Compact Petrol" as the category
        super(carID, model, "Compact Petrol");
    }

    // Implementation of Abstract Methods

    @Override
    public double getDailyRentalFee() {
        return 5000.0; //LKR 5,000
    }

    @Override
    public int getFreeKmPerDay() {
        return 100; //100 km
    }

    @Override
    public double getExtraKmCharge() {
        return 50.0; //LKR 50 per km
    }

    @Override
    public double getTaxRate() {
        return 0.10; // 10%
    }
}
