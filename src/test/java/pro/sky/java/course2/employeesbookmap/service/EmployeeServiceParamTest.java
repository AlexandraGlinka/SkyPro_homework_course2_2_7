package pro.sky.java.course2.employeesbookmap.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.employeesbookmap.model.Employee;

import java.util.List;
import java.util.stream.Stream;

public class EmployeeServiceParamTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource ("argumentsForCapitalizeTest")
    public void shouldReturnCapitalizeName(String firstName, String lastName, String expected) {
        Employee employee = new Employee(firstName, lastName, 1000, 1);
        employeeService.addEmployee(employee);
        String firstNameRight = StringUtils.capitalize(firstName.toLowerCase());
        String lastNameRight = StringUtils.capitalize(lastName.toLowerCase());
        String fullNameResult = employeeService.findEmployee(firstNameRight, lastNameRight).getFullName();

        Assertions.assertEquals(expected, fullNameResult);
    }

    private static Stream<Arguments> argumentsForCapitalizeTest() {
        return Stream.of(
                Arguments.of("ivan", "ivanov", "IvanIvanov"),
                Arguments.of("iVan", "ivanoV", "IvanIvanov"),
                Arguments.of("IVan", "iVANOV", "IvanIvanov"),
                Arguments.of("IVAN", "IVanOV", "IvanIvanov")
        );
    }
}
