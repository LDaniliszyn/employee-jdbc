package com.example.employeejdbc.db;

import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;


    public void insertEmployee(Employee employee){
        final String insertSql = "INSERT INTO EMPLOYEE (FIRST_NAME,LAST_NAME,DEPARTMENT_ID,JOB_TITLE)VALUES(?,?,?,?)";
        jdbcTemplate.update(insertSql,employee.getFirstName(),employee.getLastName(),employee.getDepartmentId(),employee.getJobTitle());
    }
    public Employee findEmployeeById(Long id){
        final String insertSql = "SELECT * FROM EMPLOYEE WHERE employee_id = ?";

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getLong("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setJobTitle(rs.getString("job_title"));
                employee.setDepartmentId(rs.getLong("department_id"));
                return employee;
            }
        };

        List<Employee> employee = jdbcTemplate.query(insertSql, rowMapper, id);

        return employee.get(0);
    }

    public void updateEmployee(EmployeeDto employeeDto)  {
        final String updateQuery = "UPDATE EMPLOYEE SET FIRST_NAME = ?, LAST_NAME = ?,DEPARTMENT_ID = ?, JOB_TITLE = ?  WHERE EMPLOYEE_ID = ?";
        jdbcTemplate.update(updateQuery,employeeDto.getFirstName(),employeeDto.getLastName(),employeeDto.getDepartmentId(),employeeDto.getJobTitle(),employeeDto.getEmployeeId());
    }

    public void deleteEmployee(Long id){
        final String insertSql = "DELETE FROM EMPLOYEE WHERE employee_id = ?";
        jdbcTemplate.update(insertSql,id);
    }
}
