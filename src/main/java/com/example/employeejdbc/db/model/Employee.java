package com.example.employeejdbc.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee  {
    private long employeeId;
    private String firstName;
    private String lastName;
    private long departmentId;
    private String jobTitle;

}
