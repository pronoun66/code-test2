package dto;

/**
 * Employee class to store all information of an employee
 */
public class Employee {

    private String firstName;
    private String lastName;
    // Annual salary is positive integer
    private Integer annualSalary;
    // Assume super rate only requires float precision
    private Float superRate;
    // Do not need to do time operation to period, so treat it as a String
    private String payPeriod;
    private Integer grossIncome;
    private Integer incomeTax;
    private Integer netIncome;
    private Integer superannuation;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

    public Float getSuperRate() {
        return superRate;
    }

    public void setSuperRate(Float superRate) {
        this.superRate = superRate;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public Integer getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(Integer grossIncome) {
        this.grossIncome = grossIncome;
    }

    public Integer getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Integer incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Integer getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(Integer netIncome) {
        this.netIncome = netIncome;
    }

    public Integer getSuperannuation() {
        return superannuation;
    }

    public void setSuperannuation(Integer superannuation) {
        this.superannuation = superannuation;
    }
}
