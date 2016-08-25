package service;

import java.util.List;

/**
 * All service relevant to employee's payslip
 */
public interface EmployPayslipService {

    /**
     * Calculate payslip based on list of annual salary data with csv format
     * @param input list of annual salary data with csv format
     * @return list of payslip information with csv format
     */
    List<String> genMonthlyPayslip(List<String> input);
}
