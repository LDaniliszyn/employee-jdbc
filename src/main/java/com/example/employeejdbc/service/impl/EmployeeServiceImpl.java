package com.example.employeejdbc.service.impl;

import com.example.employeejdbc.db.EmployeeRepository;
import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.EmployeeService;
import com.example.employeejdbc.util.EmployeeMapper;
import com.example.employeejdbc.util.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final RequestValidator requestValidator;

    public List<EmployeeDto> getEmployees() {
        return employeeRepository.getEmployees().stream()
                .map(employeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }


    @Override
    public EmployeeDto getEmployee(long id) {
        Employee employeeById = employeeRepository.findEmployeeById(id);
        return employeeMapper.mapToEmployeeDto(employeeById);
    }
    // TODO: 07.11.2022 jakos to potestowac byle by bylo.....


    @Override
    public void postEmployee(EmployeeDto employeeDto) {
        if (requestValidator.checkIfNameContainsOnlyLetters(employeeDto)){
            employeeRepository.insertEmployee(employeeMapper.mapToEmployee(employeeDto));
        }
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