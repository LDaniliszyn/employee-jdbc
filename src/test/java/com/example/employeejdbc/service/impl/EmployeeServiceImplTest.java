package com.example.employeejdbc.service.impl;

import com.example.employeejdbc.db.EmployeeRepository;
import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.EmployeeService;
import com.example.employeejdbc.service.exceptions.NotFoundException;
import com.example.employeejdbc.util.EmployeeMapper;
import com.example.employeejdbc.util.EmployeeRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    EmployeeService employeeService;
//    @Mock
//    private EmployeeRepository employeeRepository;


    private JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
    private EmployeeMapper employeeMapper = new EmployeeMapper();

    @Mock
    private EmployeeRowMapper employeeRowMapper;
    private EmployeeRepository employeeRepository =
            new EmployeeRepository(jdbcTemplate, employeeRowMapper);

    @BeforeEach
    public void setUp(){
        employeeService = new EmployeeServiceImpl(employeeRepository,employeeMapper);
    }
    @Test
    @DisplayName("should Return List Of Employees When Get Employees Is Invoke")
    public void shouldReturnListOfEmployeesWhenGetEmployeesIsInvoke(){
        Employee employee1 = Employee.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();

        Employee employee2 = Employee.builder()
                .departmentId(3L)
                .jobTitle("praca")
                .employeeId(8L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        Mockito.when(employeeRepository.getEmployees()).thenReturn(List.of(employee1,employee2));
        List<EmployeeDto> employeeDtos = assertDoesNotThrow(() -> employeeService.getEmployees());
        //then
        assertEquals(2,employeeDtos.size());
        // TODO: 03.11.2022
    }

    @Test
    void shouldThrowException() {
        // nie wchodzi do employRep. get employess w ifa bo obiekt zamokowany i od razy ma returna
        Mockito.when(jdbcTemplate.query("", employeeRowMapper)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> employeeService.getEmployees());

    }

}