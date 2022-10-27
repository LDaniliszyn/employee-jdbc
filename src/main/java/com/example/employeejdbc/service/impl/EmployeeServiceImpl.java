package com.example.employeejdbc.service.impl;

import com.example.employeejdbc.db.EmployeeRepository;
import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.EmployeeService;
import com.example.employeejdbc.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> getEmployees() {
        return employeeRepository.getEmployees().stream()
                .map(employeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }


    @Override
    public EmployeeDto getEmployee(long id) {
        Employee employeeById = employeeRepository.findEmployeeById(id);
        EmployeeDto employeeDto = employeeMapper.mapToEmployeeDto(employeeById);
        return employeeDto;
    }

    @Override
    public void postEmployee(EmployeeDto employeeDto) {
        employeeRepository.insertEmployee(employeeMapper.mapToEmployee(employeeDto));

    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        employeeRepository.updateEmployee(employeeMapper.mapToEmployee(employeeDto));

    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteEmployee(id);
    }
}
