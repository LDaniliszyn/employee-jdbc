package com.example.employeejdbc.util;

import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee mapToEmployee(final EmployeeDto employeeDto){
        return Employee.builder()
                .employeeId(employeeDto.getEmployeeId())
                .departmentId(employeeDto.getDepartmentId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .jobTitle(employeeDto.getJobTitle())
                .build();


    }
    public EmployeeDto mapToEmployeeDto(final Employee employee){
        return EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .departmentId(employee.getDepartmentId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .jobTitle(employee.getJobTitle())
                .build();

    }


    //rest rest rest !!!!
    //dokonczyć mapowanie
    //jutro zadanie z jsonem


}
