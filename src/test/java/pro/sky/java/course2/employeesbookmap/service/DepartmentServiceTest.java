package pro.sky.java.course2.employeesbookmap.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.java.course2.employeesbookmap.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        // можно вручную без моков создать сервис и передать туда сотрудников для теста
//        EmployeeService employeeService = new EmployeeServiceImpl();
//        employeeService.addEmployee(new Employee("Ivan", "Ivanov", 1000, 1));
//        departmentService = new DepartmentServiceImpl(employeeService);
        // но если зависимостей будет больше, то приходится создавать сервис для каждой
        // поэтому лучше ставить заглушку (моки) - только на employeeService

        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
        when(employeeService.getAllEmployees()).thenReturn(employeeList());

        departmentService = new DepartmentServiceImpl(employeeService);
    }

    private List<Employee> employeeList() {
        return List.of(new Employee("Ivan", "Ivanov", 1000, 1),
                new Employee("Petr", "Petrov", 2000, 2),
                new Employee("Pavel", "Sidorov", 1500, 1),
                new Employee("Alena", "Pavlova", 1800, 2));
    }

    @Test
    public void shouldReturnEmployeeWithMaxSalaryFromDep1() {
        Employee employee = departmentService.findEmployeeWithMaxSalary(1);

        Assertions.assertEquals("Pavel", employee.getFirstName());
        Assertions.assertEquals("Sidorov", employee.getLastName());
        Assertions.assertEquals(1500, employee.getSalary());
        Assertions.assertEquals(1, employee.getDepartment());
    }

    @Test
    public void shouldThrowExceptionWhenSearchEmployeeWithMaxSalaryFromDep3() {
        Assertions.assertThrows(RuntimeException.class, () ->
                departmentService.findEmployeeWithMaxSalary(3));
    }

    @Test
    public void shouldReturnEmployeeWithMinSalaryFromDep2() {
        Employee employee = departmentService.findEmployeeWithMinSalary(2);

        Assertions.assertEquals("Alena", employee.getFirstName());
        Assertions.assertEquals("Pavlova", employee.getLastName());
        Assertions.assertEquals(1800, employee.getSalary());
        Assertions.assertEquals(2, employee.getDepartment());
    }

    @Test
    public void shouldThrowExceptionWhenSearchEmployeeWithMinSalaryFromDep3() {
        Assertions.assertThrows(RuntimeException.class, () ->
                departmentService.findEmployeeWithMinSalary(3));
    }

    @Test
    public void shouldReturnEmployeesByDepartment1() {
        Collection<Employee> employees = departmentService.getEmployeesByDepartment(1);
        Collection<Employee> employeesResult = employeeList()
                .stream()
                .filter(employee -> employee.getDepartment() == 1)
                .collect(Collectors.toList());

        Assertions.assertEquals(employeesResult, employees);
    }
}
//        Assertions.assertEquals("[\n" +
//                "  {\n" +
//                "    \"firstName\": \"Ivan\",\n" +
//                "    \"lastName\": \"Ivanov\",\n" +
//                "    \"salary\": 1000,\n" +
//                "    \"department\": 1,\n" +
//                "    \"fullName\": \"IvanIvanov\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"firstName\": \"Pavel\",\n" +
//                "    \"lastName\": \"Sidorov\",\n" +
//                "    \"salary\": 1500,\n" +
//                "    \"department\": 1,\n" +
//                "    \"fullName\": \"PavelSidorov\"\n" +
//                "  }\n" +
//                "]", employees);
//    }

