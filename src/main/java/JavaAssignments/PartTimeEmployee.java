package JavaAssignments;

public class PartTimeEmployee extends Employee {

    private int hours;
    private double rate;

    public PartTimeEmployee(int hours, double rate) {
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculateSalary() {
        return hours * rate;
    }
}
