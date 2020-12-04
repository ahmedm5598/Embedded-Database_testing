package com.example.demo.controller;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    EmployeeController employeeController;

    @MockBean
    private EmployeeRepository employeeRepository;


    @Test
    void getAllEmployee() throws Exception {
        Employee employee = new Employee("ahmed","moustafa","email");
        List<Employee> list = new ArrayList<Employee>();
        list.add(employee);
        when(employeeRepository.findAll()).thenReturn(list);

//        mvc.perform(get("/api/v1/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
  //              .andExpect(jsonPath("$",hasSize(1)));
    }


    @Test
    void getEmployeeById() throws Exception{
        Optional<Employee> employee = Optional.of(new Employee("ahmed", "moustafa", "email"));
        when(employeeRepository.findById((long) 1)).thenReturn(employee);

//        mvc.perform(get("/api/v1/employees/1")).andExpect(status().isOk());

    }
    @Test
    void getEmployeeByIdNotFound() throws Exception{
        Optional<Employee> employee = Optional.of(new Employee("ahmed", "moustafa", "email"));
        when(employeeRepository.findById((long) 1)).thenReturn(employee);

//        mvc.perform(get("/api/v1/employees/5")).andExpect(status().isBadRequest());

    }
    //handling negative test cases for controller
    //integration test cases for repository

//
//    @Test
//    void createEmployee() {
//    }
//
//    @Test
//    void updateEmployee() {
//    }
//
//    @Test
//    void deleteEmployee() {
//    }
}