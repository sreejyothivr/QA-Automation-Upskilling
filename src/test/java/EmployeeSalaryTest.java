import JavaAssignments.Employee;
import JavaAssignments.FullTimeEmployee;
import JavaAssignments.PartTimeEmployee;

public class EmployeeSalaryTest {

    public static void main(String[] args) {

        Employee fullTimeEmployee = new FullTimeEmployee(50000);
        Employee partTimeEmployee = new PartTimeEmployee(20, 500);

        System.out.println("FullTime Salary: " + fullTimeEmployee.calculateSalary());
        System.out.println("PartTime Salary: " + partTimeEmployee.calculateSalary());
    }
}