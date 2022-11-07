package com.example.employeejdbc.util;

import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
@Slf4j
@RequiredArgsConstructor

public class RequestValidator {

    public void validateInsert(EmployeeDto employeeDto) {
        if (StringUtils.isBlank(employeeDto.getFirstName()) || StringUtils.isBlank(employeeDto.getLastName()) || StringUtils.isBlank(employeeDto.getJobTitle())) {
            log.error("empty fields");
            throw new BadRequestException("empty fields");
        } else if (employeeDto.getDepartmentId() <= 0) {
            log.error("must be greater than 0");
            throw new BadRequestException("department id must be greater than 0");
        }
    }
    public void validateUpdate(EmployeeDto employeeDto) {
        if (StringUtils.isBlank(employeeDto.getFirstName()) || StringUtils.isBlank(employeeDto.getLastName()) || StringUtils.isBlank(employeeDto.getJobTitle())) {
            log.error("empty fields");
            throw new BadRequestException("empty fields");
        } else if (employeeDto.getDepartmentId() == null || employeeDto.getDepartmentId() <= 0) {
            log.error("department id must be greater than 0");
            throw new BadRequestException("department id must be greater than 0");
        } else if (employeeDto.getEmployeeId() == null || employeeDto.getEmployeeId() <= 0) {
            log.error("id must be greater than 0");
            throw new BadRequestException("id must be greater than 0");
        }
    }
    public boolean checkIfNameContainsOnlyLetters(EmployeeDto employeeDto) {
        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(employeeDto.getFirstName());
        if (!matcher.matches()) {
            throw new RuntimeException("Wrong name");
        }
        return true; //void
    }
}
