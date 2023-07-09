package pro.sky.java.course2.employeesbookmap.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.employeesbookmap.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employeesbookmap.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.employeesbookmap.exceptions.InvalidNameException;
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
    public void addEmployee(Employee employee) {
        validate(employee.getFirstName(), employee.getLastName());

        employee.setFirstName(StringUtils.capitalize(employee.getFirstName().toLowerCase()));
        employee.setLastName(StringUtils.capitalize(employee.getLastName().toLowerCase()));

        if (employeesMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeesMap.put(employee.getFullName(), employee);
    }

    @Override
    public short removeEmployee(String firstName, String lastName) {
        if (!employeesMap.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует");
        }
        employeesMap.remove(firstName + lastName);
        return 0;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (!employeesMap.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует");
        }
        return employeesMap.get(firstName + lastName);
    }

    @Override
    public Collection<Employee> showEmployees() {
        return Collections.unmodifiableCollection(employeesMap.values());
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeesMap.values();
    }

    private void validate(String... names) {
        for (String name : names) { //names.iter
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException("Name must contain only letters");
            }
        }
    }
}
