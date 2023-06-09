package pro.sky.java.course2.employeesbookmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.employeesbookmap.model.Employee;
import pro.sky.java.course2.employeesbookmap.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> showEmployees() {
        return employeeService.showEmployees();
    }

    @GetMapping("/add")
    public void addEmployee(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam Integer salary,
                            @RequestParam Integer department) {
        employeeService.addEmployee(new Employee(firstName, lastName, salary, department));
    }

    @GetMapping("/remove")
    public void removeEmployee(@RequestParam String firstName,
                               @RequestParam String lastName) {
        employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/getAll")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}

