package com.example.employeejdbc.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee  {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private long departmentId;
    private String jobTitle;
}
