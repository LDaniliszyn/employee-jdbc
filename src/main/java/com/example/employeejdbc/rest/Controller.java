package com.example.employeejdbc.rest;

import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.EmployeeService;
import com.example.employeejdbc.service.impl.EmployeeServiceImpl;
import com.example.employeejdbc.util.RequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final EmployeeService employeeService;
    private final RequestValidator requestValidator;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping("/employee")
    public ResponseEntity<Void> postEmployee(@RequestBody final EmployeeDto employeeDto) {
        //log.info("post /employee with body: {}", employeeDto);
        requestValidator.validateInsert(employeeDto);
        employeeService.postEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final long id) {
        log.info("get /employee/{}", id);
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee")
    public ResponseEntity<Void> updateEmployee(@RequestBody final EmployeeDto employeeDto) {
        employeeService.updateEmployee(employeeDto);
        return ResponseEntity.ok().build();
    }
}
