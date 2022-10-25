package com.example.employeejdbc.rest;

import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.EmployeeService;
import com.example.employeejdbc.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping("/employee")
    public ResponseEntity<Void> postEmployee(@RequestBody final EmployeeDto employeeDto){
        employeeService.postEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final long id){
        System.out.println("tu wlazlo");
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/employee")
    public ResponseEntity<Void> updateEmployee(@RequestBody final EmployeeDto employeeDto){
        employeeService.updateEmployee(employeeDto);
        return ResponseEntity.ok().build();
    }


}



