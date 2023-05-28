package pro.sky.java.course2.service;

import pro.sky.java.course2.model.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);

    void removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);
}
