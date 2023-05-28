package pro.sky.java.course2.employeesbookmap.service;

import pro.sky.java.course2.employeesbookmap.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    void addEmployee(Employee employee);

    void removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> showEmployees();
}
