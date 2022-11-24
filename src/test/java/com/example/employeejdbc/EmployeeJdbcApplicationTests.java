package com.example.employeejdbc;


import com.example.employeejdbc.db.model.Employee;
import com.example.employeejdbc.rest.dto.EmployeeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeJdbcApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();
	@Test
	public void shouldAddEmployee() throws Exception {
		EmployeeDto employeedto = EmployeeDto.builder()
				.departmentId(2L)
				.jobTitle("praca")
				.firstName("ala")
				.lastName("kowalska")
				.build();

		mockMvc.perform(post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employeedto)))
				.andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void shouldThrowBadRequestException() throws Exception {
		EmployeeDto employeedto = EmployeeDto.builder()
				.departmentId(2L)
				.jobTitle("praca")
				.lastName("kowalska")
				.build();

		mockMvc.perform(post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employeedto)))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void shouldReturnEmployee() throws Exception {

		mockMvc.perform(get("/employee/1"))

				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers
						.content()
						.contentType("application/json"))
				.andExpect(jsonPath("$.firstName").value("test"))
				.andExpect(jsonPath("$.firstName").value("test")) //doposac
				.andExpect(jsonPath("$.firstName").value("test"))
				.andExpect(jsonPath("$.firstName").value("test"))
				.andDo(MockMvcResultHandlers.print());
	}
	// TODO: 17.11.2022 testy do controlerow

}
