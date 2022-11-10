package com.example.employeejdbc.util;

import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {
    EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    void shouldCorrectlyMapDto() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();

        Employee employee = Employee.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();

        //when
        Employee result = assertDoesNotThrow(() -> employeeMapper.mapToEmployee(employeeDto));
        //then
        assertEquals(employee, result);
    }


    @Test
    void shouldCorrectlyMapEmployee() {
        //given
        Employee employee = Employee.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();

        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();

        //when
        EmployeeDto resoulteEmployeeDto = assertDoesNotThrow(() -> employeeMapper.mapToEmployeeDto(employee));

        //then
        assertEquals(employeeDto,resoulteEmployeeDto);

    }


}