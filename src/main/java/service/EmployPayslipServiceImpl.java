package service;

import dto.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of EmployPayslipService
 */
public class EmployPayslipServiceImpl implements EmployPayslipService {

    @Override
    public List<String> genMonthlyPayslip(List<String> input) {
        List<Employee> employees = new ArrayList<>();
        input.forEach(data -> employees.add(toEmployee(data)));
        employees.forEach(e -> {
            e.setGrossIncome(grossIncome(e.getAnnualSalary()));
            e.setIncomeTax(incomeTax(e.getAnnualSalary()));
            e.setNetIncome(netIncome(e.getGrossIncome(), e.getIncomeTax()));
            e.setSuperannuation(superannuation(e.getGrossIncome(), e.getSuperRate()));
        });
        List<String> result = new ArrayList<>();
        employees.forEach(e -> result.add(toCsv(e)));
        return result;
    }

    private Employee toEmployee(String csvData) {
        String[] dataArray = csvData.split(",");
        if (dataArray.length != 5) {
            throw new IllegalArgumentException();
        }

        int salary = Integer.valueOf(dataArray[2].trim());
        if (salary < 0) {
            throw new IllegalArgumentException();
        }

        if (!dataArray[3].contains("%")) {
            throw new IllegalArgumentException();
        }

        float superRate = Float.valueOf(dataArray[3].replace("%", "").trim()) / 100;
        if (superRate < 0) {
            throw new IllegalArgumentException();
        }

        Employee employee = new Employee();
        employee.setFirstName(dataArray[0].trim());
        employee.setLastName(dataArray[1].trim());
        employee.setAnnualSalary(salary);
        employee.setSuperRate(superRate);
        employee.setPayPeriod(dataArray[4].trim());
        return employee;
    }

    private String toCsv(Employee employee) {
        return employee.getFirstName() + " "
                + employee.getLastName() + ","
                + employee.getPayPeriod() + ","
                + employee.getGrossIncome() + ","
                + employee.getIncomeTax() + ","
                + employee.getNetIncome() + ","
                + employee.getSuperannuation();
    }

    private int grossIncome(int annualSalary) {
        return Math.round((float) annualSalary / 12);
    }

    private int incomeTax(int annualSalary) {
        if (annualSalary <= 18_200) {
            return 0;
        } else if (annualSalary <= 37_000) {
            return incomeTaxFormula(annualSalary, 0, 18_200, 0.19f);
        } else if (annualSalary <= 80_000) {
            return incomeTaxFormula(annualSalary, 3_572, 37_000, 0.325f);
        } else if (annualSalary <= 180_000) {
            return incomeTaxFormula(annualSalary, 17_547, 80_000, 0.37f);
        } else {
            return incomeTaxFormula(annualSalary, 54_547, 180_000, 0.45f);
        }
    }

    private int incomeTaxFormula(int annualSalary, int baseTax, int baseSalary, float rate) {
        return Math.round((rate * (annualSalary - baseSalary) + baseTax) / 12);
    }

    private int netIncome(int grossIncome, int incomeTax) {
        return grossIncome - incomeTax;
    }

    private int superannuation(int grossIncome, float superRate) {
        return Math.round(superRate * grossIncome);
    }
}
