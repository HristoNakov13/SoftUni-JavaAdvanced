package CompanyRoster;

import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, List<Employee>> departments = new HashMap<>();
        int inputs = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < inputs; i++) {

            String[] employeeDetails = scanner.nextLine().split(" ");

            Employee addEmployee = createEmployee(employeeDetails);
            departments.putIfAbsent(addEmployee.getDepartment(), new ArrayList<>());
            departments.get(addEmployee.getDepartment()).add(addEmployee);
        }

        departments.entrySet().stream().sorted((dep1, dep2) -> {

            double averageSalaryDep1 = dep1.getValue().stream().mapToDouble(Employee::getSalary).sum();
            double averageSalaryDep2 = dep2.getValue().stream().mapToDouble(Employee::getSalary).sum();
            return Double.compare(averageSalaryDep2, averageSalaryDep1);

        }).limit(1).forEach(department -> {

            System.out.println("Highest Average Salary: " + department.getKey());
            department.getValue().stream().sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                    .forEach(employee -> System.out.println(String.format("%s %.2f %s %d",
                            employee.getName(), employee.getSalary(), employee.getEmail(), employee.getAge())));
        });
    }
    private static Employee createEmployee (String[] details) {

        String name = details[0];
        double salary = Double.parseDouble(details[1]);
        String position = details[2];
        String department = details[3];
        int age;

        Employee newEmployee = new Employee(name, salary, position, department);


        switch (details.length) {
            case 5:
                try {
                age = Integer.parseInt(details[4]);
                newEmployee = new Employee (name, salary, position, department, age);
                }
                catch (Exception e) {
                    newEmployee = new Employee (name, salary, position, department, details[4]);
                }
                break;
            case 6:
                age = Integer.parseInt(details[5]);
                String email = details[4];
                newEmployee = new Employee (name, salary, position, department, email, age);
        }
        return newEmployee;

    }
}
