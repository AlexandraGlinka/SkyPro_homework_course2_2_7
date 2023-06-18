package pro.sky.java.course2.employeesbookmap.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.employeesbookmap.model.Employee;
import pro.sky.java.course2.employeesbookmap.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalary(@RequestParam Integer department) {
        return departmentService.findEmployeeWithMaxSalary(department);
    }
    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalary(@RequestParam Integer department) {
        return departmentService.findEmployeeWithMinSalary(department);
    }
    @GetMapping(value = "/all", params = "department") // только при наличии параметра
    public Collection<Employee> getEmployeesByDepartment(@RequestParam Integer department) {
        return departmentService.getEmployeesByDepartment(department);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return departmentService.getEmployeesGroupedByDepartment();
    }
}
