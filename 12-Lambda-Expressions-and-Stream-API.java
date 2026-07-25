import java.util.*;
import java.util.stream.*;

class Employee {

    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return id + " " + name + " " + department + " " + salary;
    }
}

public class EmployeeAnalytics {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(

                new Employee(101, "Rahul", "CSE", 55000),
                new Employee(102, "Sneha", "ECE", 62000),
                new Employee(103, "Kiran", "CSE", 48000),
                new Employee(104, "Divya", "MECH", 51000),
                new Employee(105, "Arjun", "ECE", 70000)

        );

        System.out.println("---- All Employees ----");

        employees.forEach(e -> System.out.println(e));

        System.out.println("\n---- Salary Above 50000 (High to Low) ----");

        employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .forEach(e -> System.out.println(e.getName() + " -> " + e.getSalary()));

        System.out.println("\n---- Employee Names ----");

        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(names);

        System.out.println("\n---- Employees Grouped by Department ----");

        Map<String, List<String>> group = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));

        group.forEach((dept, list) ->
                System.out.println(dept + " : " + list));

        System.out.println("\n---- Average Salary per Department ----");

        Map<String, Double> avgSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        avgSalary.forEach((dept, avg) ->
                System.out.printf("%s : %.2f\n", dept, avg));

        double totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);

        System.out.printf("\nTotal Salary Paid : %.2f\n", totalSalary);

        long cseCount = employees.stream()
                .filter(e -> e.getDepartment().equals("CSE"))
                .count();

        System.out.println("Number of CSE Employees : " + cseCount);

        Optional<Employee> highest = employees.stream()
                .max(Comparator.comparing(Employee::getSalary));

        if (highest.isPresent()) {

            Employee e = highest.get();

            System.out.println("Highest Paid : " + e.getName() + " (" + e.getSalary() + ")");
        }

    }
}