package com.example.demo.repository;
import java.util.Optional;
import java.util.Random;

import com.example.demo.DbConfig;
import com.example.demo.DemoApplication;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DbConfig.class})
@ActiveProfiles("DaoTest")
public class PostgresEmbeddedDaoTestingApplicationTests {


    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() {


        Employee employee = new Employee("ahmed", "mo", "d");
        //employee.setId(20);
        System.out.println(employee.getFirstName());
        Employee saveInDb = employeeRepository.save(employee);

        Assert.assertTrue(employeeRepository.findByFirstNameStartingWith("ah") != null);
        Assert.assertTrue(employeeRepository.findById(employee.getId()) != null);

    }


}