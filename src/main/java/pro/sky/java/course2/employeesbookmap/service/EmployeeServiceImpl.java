package pro.sky.java.course2.employeesbookmap.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employeesbookmap.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employeesbookmap.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.employeesbookmap.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final Map<String, Employee> employeesMap;
    public EmployeeServiceImpl() {
        this.employeesMap = new HashMap<>();
    }
    @Override
    public Collection<Employee> showEmployees() {
        return Collections.unmodifiableCollection(employeesMap.values());
    }
    @Override
    public void addEmployee(Employee employee) {
        if (employeesMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employeesMap.put(employee.getFullName(), employee);
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        if (!employeesMap.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует");
        }
        employeesMap.remove(firstName + lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (!employeesMap.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует");
        }
        return employeesMap.get(firstName + lastName);
    }


}
