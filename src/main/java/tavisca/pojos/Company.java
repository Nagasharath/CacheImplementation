package tavisca.pojos;

public class Company {

    private String companyName;

    private String companyLocation;

    private int noOfEmployees;

    private String CEOName;

    public Company() {

    }

    public String getCompanyName() {
        return companyName;
    }

    public Company(String companyName, String companyLocation, int noOfEmployees, String CEOName) {
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.noOfEmployees = noOfEmployees;
        this.CEOName = CEOName;
    }



    public String getCompanyLocation() {
        return companyLocation;
    }



    public int getNoOfEmployees() {
        return noOfEmployees;
    }



    public String getCEOName() {
        return CEOName;
    }


}
