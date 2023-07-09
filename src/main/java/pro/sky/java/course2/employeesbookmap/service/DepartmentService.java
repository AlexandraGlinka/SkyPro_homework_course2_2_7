package pro.sky.java.course2.employeesbookmap.service;

import pro.sky.java.course2.employeesbookmap.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findEmployeeWithMaxSalary(Integer department);
    Employee findEmployeeWithMinSalary(Integer department);
    Collection<Employee> getEmployeesByDepartment(Integer department);
    Map<Integer, List<Employee>> getEmployeesGroupedByDepartment();

}
