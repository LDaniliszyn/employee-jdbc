package com.example.employeejdbc.db;

import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.service.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void shouldThrowNotFoundExceptionWhenCantFindEmployeeById(){

        //assertThrows(NotFoundException.class, () -> employeeRepository.findEmployeeById(12L));
        assertThrows(EmptyResultDataAccessException.class, () -> employeeRepository.findEmployeeById(12L));
    }
    // w emprep find by id czy poprawnie jest robic w try catch czy tak jak teraz? było if(employee == nul) ale query for object nie zeraca nigdy nula

    @Test
    public void shouldFindEmployeeById(){
        Employee employee1 = Employee.builder()
                .employeeId(1L)
                .jobTitle("job title test")
                .lastName("last name test")
                .firstName("first name test")
                .departmentId(3L)
                .build();

        Employee employee2 = Employee.builder()
                .employeeId(2L)
                .jobTitle("job title test")
                .lastName("last name test")
                .firstName("first name test")
                .departmentId(3L)
                .build();

        employeeRepository.insertEmployee(employee1);
        employeeRepository.insertEmployee(employee2);

        Employee employeeById = employeeRepository.findEmployeeById(2L);
        assertEquals(employee2,employeeById);
    }


    @Test
    public void shouldClearDatabaseAndThrowNotFoundException(){
        Employee employee = Employee.builder()
                .employeeId(1L)
                .jobTitle("job title test")
                .lastName("last name test")
                .firstName("first name test")
                .departmentId(3L)
                .build();

        employeeRepository.insertEmployee(employee);
        employeeRepository.clearDatabase();

        assertThrows(NotFoundException.class, () -> employeeRepository.getEmployees());
    }

    @Test
    public void shouldInsertNewEmployee(){
        employeeRepository.clearDatabase();

        Employee employee = Employee.builder()
                .employeeId(1L)
                .jobTitle("job title test")
                .lastName("last name test")
                .firstName("first name test")
                .departmentId(3L)
                .build();


        employeeRepository.insertEmployee(employee);
        List<Employee> employees = employeeRepository.getEmployees();
        assertEquals(employee,employees.get(0));
        assertEquals(1,employees.size());
    }
    // TODO: 17.11.2022 do naprawy. V2_insert_employee ustawione id na sztywno, bo zajmowalo pierwszy index w bazie

    @Test
    public void shouldUpdateEmployee(){

        Employee employeeUpdated = Employee.builder()
                .employeeId(1L)
                .jobTitle("job title test")
                .lastName("last name test")
                .firstName("first name test")
                .departmentId(3L)
                .build();

        employeeRepository.updateEmployee(employeeUpdated);
        Employee employeeById = employeeRepository.findEmployeeById(1L);
        assertEquals(employeeUpdated,employeeById);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenEmployeeIsNotPresent(){
        Employee employeeUpdated = Employee.builder()
                .employeeId(2L)
                .build();
        assertThrows(NotFoundException.class, ()-> employeeRepository.updateEmployee(employeeUpdated));
    }


}