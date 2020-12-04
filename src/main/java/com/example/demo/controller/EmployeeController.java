package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSpecification;
import com.example.demo.model.SearchCriteria;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Path;


@RestController
@RequestMapping("/api/v2")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //getEmployees
    @GetMapping("employees")
    public List<Employee> getAllEmployee(){
        System.out.println("############################################################");
        System.out.println("************************************************************");
        List<String> types = new ArrayList<>();
        types.add("moustafa");
        types.add("kk");
        types.add("money");
        EmployeeSpecification spec = new EmployeeSpecification(new SearchCriteria("keywordsString",":","%mayor%"),null);
        EmployeeSpecification spec1 = new EmployeeSpecification(new SearchCriteria("lastName",null,"'metro:new-england:[a-z\\-\\_]*[^:]$'"),null);
        return this.employeeRepository.findAll(spec1);
    }
    //get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId){
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        //  .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        if(!employee.isPresent())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(employee.get());
    }

    //save employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @RequestBody Employee employeeDetails){
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(!employee.isPresent())
             return ResponseEntity.badRequest().body("Employee not found for this id");

        employee.get().setEmail(employeeDetails.getEmail());
        employee.get().setLastName(employeeDetails.getLastName());
        employee.get().setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee.get());
        return ResponseEntity.ok(updatedEmployee);
    }
    //delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if(!employee.isPresent())
            return ResponseEntity.badRequest().body("Employee not found for this id");
        employeeRepository.delete(employee.get());
        return ResponseEntity.ok().body("Employee deleted");
    }
}