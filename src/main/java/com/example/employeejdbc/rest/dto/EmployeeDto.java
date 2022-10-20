package com.example.employeejdbc.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private long employeeId;
    private String firstName;
    private String lastName;
    private long departmentId;
    private String jobTitle;
}

