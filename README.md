### Assumption
1. Input is list of annual salary data with csv format
2. Output is list of payslip information data with csv format
3. No comma in first name and last name in input
4. No need to do time operation in pay period column, treat it as a simple String
5. Annual salary input should be non-negative integer, which is not bigger than Integer.MAX
6. Super rate input should come with "%" character
7. Every row n input should have complete data in 5 columns

### How To Run
#####BUILD
$ mvn clean install => build the application

#####RUN
see src/test/java/service/EmployPayslipServiceImplTest.java to check the usage of EmployPayService

### Dependency
Java 1.8

Junit 4.12