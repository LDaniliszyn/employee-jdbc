package com.example.employeejdbc.service.impl;

import com.example.employeejdbc.db.EmployeeRepository;
import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.EmployeeService;
import com.example.employeejdbc.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> getEmployees(){
        EmployeeDto employee1 = EmployeeDto.builder().employeeId(1).firstName("jan").lastName("koalski").departmentId(1).jobTitle("jdj").build();
        EmployeeDto employee2 = EmployeeDto.builder().employeeId(2).firstName("piotr").lastName("nowak").departmentId(1).jobTitle("jdj").build();
        return List.of(employee1,employee2);
    }

    @Override
    public EmployeeDto getEmployee(long id) {
        return null;
    }

    @Override
    public void postEmployee(EmployeeDto employeeDto) {
        employeeRepository.insertEmployee(employeeMapper.mapToEmployee(employeeDto));

    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {

    }

    @Override
    public void deleteEmployee(long id) {

    }
}
