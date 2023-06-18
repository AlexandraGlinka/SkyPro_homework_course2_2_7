package pro.sky.java.course2.employeesbookmap.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employeesbookmap.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalary(Integer department) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                //.filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                // отбираем int значения
                // .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException("В отделе нет сотрудников"));
    }

    @Override
    public Employee findEmployeeWithMinSalary(Integer department) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException("В отделе нет сотрудников"));
    }

    @Override
    public Collection<Employee> getEmployeesByDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
