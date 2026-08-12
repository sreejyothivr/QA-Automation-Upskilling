package JavaAssignments;

import JavaAssignments.Employee;

public class FullTimeEmployee extends Employee {

    private final double baseSalary;

    public FullTimeEmployee(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }
}