package pro.sky.java.course2.employeesbookmap.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.employeesbookmap.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employeesbookmap.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.employeesbookmap.model.Employee;
import pro.sky.java.course2.employeesbookmap.service.EmployeeService;
import pro.sky.java.course2.employeesbookmap.service.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployeeWhenItDoesntExist() {
        Employee employeeExpected = new Employee("Ivan", "Ivanov", 1000, 1);
        employeeService.addEmployee(employeeExpected);

        Employee employeeResult = employeeService.findEmployee("Ivan", "Ivanov");

        Assertions.assertEquals(employeeExpected, employeeResult);
    }

    @Test
    public void shouldThrowExceptionWhenEmployeeExists() {
        Employee employeeExpected = new Employee("Ivan", "Ivanov", 1000, 1);
        employeeService.addEmployee(employeeExpected);

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
            employeeService.addEmployee(employeeExpected));
    }

    @Test
    public void shouldRemoveEmployeeWhenItDoesntExist() {
        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);
        employeeService.addEmployee(employee);
        employeeService.removeEmployee("Ivan", "Ivanov");

        Assertions.assertFalse(employeeService.getAllEmployees().contains(employee));
    }

    @Test
    public void shouldThrowExceptionWhenEmployeeDoesntExistsWhileRemove() {
//        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);
//        employeeService.addEmployee(employee);

        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.removeEmployee("Ivan", "Ivanov"));
    }

    @Test
    public void shouldThrowExceptionWhenEmployeeDoesntExistsWhileFind() {

        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee("Ivan", "Ivanov"));
    }
}
