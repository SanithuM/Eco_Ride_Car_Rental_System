public class K2559097_Customer {

    // Attributes
    private String nicOrPassport;
    private String name;
    private String contactNumber;
    private String email;

    // Constructor
    public  K2559097_Customer(String nicOrPassport, String name, String contactNumber, String email) {
        this.nicOrPassport = this.nicOrPassport;
        this.name = this.name;
        this.contactNumber = this.contactNumber;
        this.email = this.email;
    }

    // Getters
    public String getNicOrPassport() {

        return nicOrPassport;
    }

    public String getName() {

        return name;
    }

    public String getContactNumber() {

        return contactNumber;
    }

    public String getEmail() {

        return email;
    }

    // helper method to display customer details

    @Override
    public String toString() {
        return "Customer [Name: " + name + ", NIC/Passport: " + nicOrPassport + ", Contact: " + contactNumber + "]";
    }
}
