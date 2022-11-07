package com.example.employeejdbc.util;

import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.example.employeejdbc.service.exceptions.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidatorTest {
    RequestValidator requestValidator = new RequestValidator();

    @Test
    @DisplayName("should Throw Bad Request Exception When Empty Fields Insert")
    void shouldThrowBadRequestExceptionWhenEmptyFieldsInsert() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle(null)
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        Exception exception = assertThrows(BadRequestException.class, ()->requestValidator.validateInsert(employeeDto));
        //then
        assertEquals("empty fields", exception.getMessage());
    }

    @Test
    @DisplayName("should Does Not Throw Bad Request Exception When No Empty Fields Insert")
    void shouldDoesNotThrowBadRequestExceptionWhenNoEmptyFieldsInsert() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("ll")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        assertDoesNotThrow(()->requestValidator.validateInsert(employeeDto));
        //then
    }

    @Test
    @DisplayName("should Throw Bad Request Exception When Department Id Lower Than Zero Insert")
    void shouldThrowBadRequestExceptionWhenDepartmentIdLowerThanZeroInsert() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(-2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        Exception exception = assertThrows(BadRequestException.class, ()->requestValidator.validateInsert(employeeDto));
        //then
        assertEquals("department id must be greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("should Does Not Throw Bad Request Exception When Department Id Greater Than Zero Insert")
    void shouldDoesNotThrowBadRequestExceptionWhenDepartmentIdGreaterThanZeroInsert() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
       assertDoesNotThrow(()->requestValidator.validateInsert(employeeDto));
        //then
    }




    @Test
    @DisplayName("should Throw Bad Request Exception When Empty Fields Update")
    void shouldThrowBadRequestExceptionWhenEmptyFieldsUpdate() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle(null)
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        Exception exception = assertThrows(BadRequestException.class, ()->requestValidator.validateUpdate(employeeDto));
        //then
        assertEquals("empty fields", exception.getMessage());
    }

    @Test
    @DisplayName("should Does Not Throw Bad Request Exception When No Empty Fields Update")
    void shouldDoesNotThrowBadRequestExceptionWhenNoEmptyFieldsUpdate() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("ll")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        assertDoesNotThrow(()->requestValidator.validateUpdate(employeeDto));
        //then
    }

    @Test
    @DisplayName("should Throw Bad Request Exception When Department Id Lower Than Zero Update")
    void shouldThrowBadRequestExceptionWhenDepartmentIdLowerThanZeroUpdate() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(-2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        Exception exception = assertThrows(BadRequestException.class, ()->requestValidator.validateUpdate(employeeDto));
        //then
        assertEquals("department id must be greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("should Does Not Throw Bad Request Exception When Department Id Greater Than Zero Update")
    void shouldDoesNotThrowBadRequestExceptionWhenDepartmentIdGreaterThanZeroUpdate() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        assertDoesNotThrow(()->requestValidator.validateUpdate(employeeDto));
        //then
    }

    @Test
    void shouldThrowBadRequestExceptionWhenIdLowerThanZeroUpdate(){
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(-7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        BadRequestException badRequestException = assertThrows(BadRequestException.class, () -> requestValidator.validateUpdate(employeeDto));

        //then
        assertEquals("id must be greater than 0",badRequestException.getMessage());
    }

    @Test
    @DisplayName("should Does Not Throw Bad Request Exception When Id Greater Than Zero Update")
    void shouldDoesNotThrowBadRequestExceptionWhenIdGreaterThanZeroUpdate(){
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        assertDoesNotThrow(() ->requestValidator.validateUpdate(employeeDto));
        //then
    }




    @Test
    @DisplayName("should Throw Runtime Exception When Wrong Name")
    void shouldThrowRuntimeExceptionWhenWrongName(){
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("a4345la")
                .lastName("kowalska")
                .build();
        //when
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> requestValidator.checkIfNameContainsOnlyLetters(employeeDto));

        //then
        assertEquals("Wrong name",runtimeException.getMessage());
    }

    @Test
    @DisplayName("should Does Not Throw Runtime Exception When Correct Name")
    void shouldDoesNotThrowRuntimeExceptionWhenCorrectName(){
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .departmentId(-2L)
                .jobTitle("praca")
                .employeeId(7L)
                .firstName("ala")
                .lastName("kowalska")
                .build();
        //when
        assertDoesNotThrow(()->requestValidator.checkIfNameContainsOnlyLetters(employeeDto));
        //then

    }

}