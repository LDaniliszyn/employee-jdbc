package com.example.employeejdbc.service;

import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getEmployees();
    EmployeeDto getEmployee(long id);
    void postEmployee(EmployeeDto employeeDto);
    void updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee(long id);
}
