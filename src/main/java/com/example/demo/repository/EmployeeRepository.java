package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {
    @Query(value = "SELECT * FROM employees WHERE last_name=?1 ORDER BY first_name", nativeQuery = true)
    List<Employee> findEmployeesByLastName(String last_name);

    @Query(
            value = "SELECT * FROM employees ORDER BY id",
            countQuery = "SELECT count(*) FROM employees",
            nativeQuery = true)
    Page<Employee> findAllUsersWithPagination(Pageable pageable);

    List<Employee> findByFirstNameStartingWith(String prefix);



}


