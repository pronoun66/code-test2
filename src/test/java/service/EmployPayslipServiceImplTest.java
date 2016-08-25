package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployPayslipServiceImplTest {

    private EmployPayslipServiceImpl employPayslipService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        employPayslipService = new EmployPayslipServiceImpl();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void genMonthlyPayslip() throws Exception {
        List<String> input = new ArrayList<>();
        input.add("Donald,Trump,18000,0%,01 March – 31 March");
        input.add("Barack,Obama,37000,8%,01 March – 31 March");
        input.add("David,Rudd,60050,9%,01 March – 31 March");
        input.add("Jerry,Liao,80000,9.5%,01 March – 31 March");
        input.add("Ryan,Chen,120000,10%,01 March – 31 March");
        input.add("Steve,Jobs,180000,10%,01 March – 31 March");
        input.add("Bill,Gates,300000,20%,01 March – 31 March");


        List<String> expected = new ArrayList<>();
        expected.add("Donald Trump,01 March – 31 March,1500,0,1500,0");
        expected.add("Barack Obama,01 March – 31 March,3083,298,2785,247");
        expected.add("David Rudd,01 March – 31 March,5004,922,4082,450");
        expected.add("Jerry Liao,01 March – 31 March,6667,1462,5205,633");
        expected.add("Ryan Chen,01 March – 31 March,10000,2696,7304,1000");
        expected.add("Steve Jobs,01 March – 31 March,15000,4546,10454,1500");
        expected.add("Bill Gates,01 March – 31 March,25000,9046,15954,5000");

        List<String> result = employPayslipService.genMonthlyPayslip(input);
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    public void calculatePayment_IncompleteData() throws Exception {
        List<String> input = new ArrayList<>();
        // Incomplete data without pay period
        input.add("David,Rudd,60050,9%");

        // Expect
        exception.expect(IllegalArgumentException.class);

        employPayslipService.genMonthlyPayslip(input);
    }

    @Test
    public void calculatePayment_InvalidAnnualSalaryFormat() throws Exception {
        List<String> input = new ArrayList<>();
        // Invalid annual salary format
        input.add("David,Rudd,XXX Wrong Annual Salary XXX,9%,01 March – 31 March");

        // Expect
        exception.expect(IllegalArgumentException.class);

        employPayslipService.genMonthlyPayslip(input);
    }

    @Test
    public void calculatePayment_InvalidAnnualSalaryValue() throws Exception {
        List<String> input = new ArrayList<>();
        // Negative annual salary
        input.add("David,Rudd,-60050,9%,01 March – 31 March");

        // Expect
        exception.expect(IllegalArgumentException.class);

        employPayslipService.genMonthlyPayslip(input);
    }

    @Test
    public void calculatePayment_InvalidSuperRateFormat() throws Exception {
        List<String> input = new ArrayList<>();
        // Super rate doesn't have "%"
        input.add("David,Rudd,60050,9,01 March – 31 March");

        // Expect
        exception.expect(IllegalArgumentException.class);

        employPayslipService.genMonthlyPayslip(input);
    }

    @Test
    public void calculatePayment_InvalidSuperValue() throws Exception {
        List<String> input = new ArrayList<>();
        // Negative super rate
        input.add("David,Rudd,60050,-9%,01 March – 31 March");

        // Expect
        exception.expect(IllegalArgumentException.class);

        employPayslipService.genMonthlyPayslip(input);
    }
}