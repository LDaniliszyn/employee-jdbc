package com.example.employeejdbc.db;

import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.service.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void shouldInsertNewEmployee(){
        Employee employee = Employee.builder()
                .jobTitle("ds")
                .lastName("fds")
                .firstName("test")
                .departmentId(23L)
                .build();

        assertThrows(NotFoundException.class, ()-> employeeRepository.getEmployees());

        employeeRepository.insertEmployee(employee);
        List<Employee> employees = employeeRepository.getEmployees();
        assertEquals(employee,employees.get(0));
        assertEquals(1,employees.size());
    }
}

// deleteall
// beforeeach odpalająca usuwanie bazy danych
// tutaj testy
