public class Employee {
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    public String toFileString() {
        return id + "," + name + "," + designation + "," + salary;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        return new Employee(parts[1], Integer.parseInt(parts[0]), parts[2], Double.parseDouble(parts[3]));
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
    }
}