package com.example.employeejdbc.rest;

import com.example.employeejdbc.service.EmployeeService;
import com.example.employeejdbc.util.RequestValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {
    EmployeeService employeeService;
    RequestValidator requestValidator = new RequestValidator();
    Controller controller = new Controller(employeeService,requestValidator);

    @LocalServerPort
    private int serverPort;

    @Test
    @DisplayName("check If GetEmployees Return Correct Http")
    void checkIfGetEmployeesReturnCorrectHttp() {
        //given



        //when
        controller.getEmployees().getStatusCode();

        //then

    }
}