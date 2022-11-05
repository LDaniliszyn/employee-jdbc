package com.example.employeejdbc.db;

import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.exceptions.NotFoundException;
import com.example.employeejdbc.util.EmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeRowMapper employeeRowMapper;


    public void insertEmployee(Employee employee) {
        final String insertSql = "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,DEPARTMENT_ID,JOB_TITLE)VALUES(?,?,?,?)";
        jdbcTemplate.update(insertSql, employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle());
    }

    public Employee findEmployeeById(Long id) {
        final String insertSql = "SELECT * FROM EMPLOYEE WHERE employee_id = ?";
        Employee employee = jdbcTemplate.queryForObject(insertSql, employeeRowMapper, id);
        if (employee == null){
            throw new NotFoundException("not found");
        }
        return employee;
    }

    public void updateEmployee(Employee employee) {
        final String updateQuery = "UPDATE EMPLOYEE SET FIRST_NAME = ?, LAST_NAME = ?,DEPARTMENT_ID = ?, JOB_TITLE = ?  WHERE EMPLOYEE_ID = ?";
        int updatedCount = jdbcTemplate.update(updateQuery, employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(), employee.getEmployeeId());
        if (updatedCount == 0) {
            throw new NotFoundException("not found");
        }
    }

    public void deleteEmployee(Long id) {
        final String insertSql = "DELETE FROM EMPLOYEE WHERE employee_id = ?";
        int deletedCount = jdbcTemplate.update(insertSql, id);
        if (deletedCount == 0 ){
            throw new NotFoundException("not found");
        }
        // TODO: 05.11.2022
        //given when jdbcTemplate.update then return 0
        //when deleteEmployee
        //then czy wyjatek
    }

    public List<Employee> getEmployees() {
        final String insertSql = "SELECT employee_id, first_name, last_name, department_id, job_title FROM public.employee;";
        List<Employee> employee = jdbcTemplate.query(insertSql, employeeRowMapper);
        if (employee.isEmpty()){
            throw new NotFoundException("not found");
        }
        return employee;

    }
}
