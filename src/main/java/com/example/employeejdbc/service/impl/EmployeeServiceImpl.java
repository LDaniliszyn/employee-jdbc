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
        return employeeMapper.mapToEmployeeDto(employeeById);
    }
    // TODO: 03.11.2022 mockito then throw sprawdzić czy dostanie wyjatek


    @Override
    public void postEmployee(EmployeeDto employeeDto) {
        //metode sprawdzajacą wiek?
        employeeRepository.insertEmployee(employeeMapper.mapToEmployee(employeeDto));
        // TODO: 05.11.2022 sprawdzić czy sie wykona jeden raz przez verify  when większy niż 18
        // TODO: 05.11.2022 napiszać wyjątejątek jak za mały wiek
        // TODO: 05.11.2022  sprawdzić czy jak młodszy to czy da wyjatek
        // TODO: 05.11.2022

        //given employ 18+
        //when postEmployee()
        //then verify employRepo 1 times
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