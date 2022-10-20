package com.example.employeejdbc.db;

import com.example.employeejdbc.db.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public void insertEmployee(Employee employee){
        final String insertSql = "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,DEPARTMENT_ID,JOB_TITLE)VALUES(?,?,?,?)";
        jdbcTemplate.update(insertSql,employee.getFirstName(),employee.getLastName(),employee.getDepartmentId(),employee.getJobTitle());
    }
}
