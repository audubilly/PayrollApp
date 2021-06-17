package com.example.payroll.data.repository;

import com.example.payroll.data.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveEmployeetoDBTest(){
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setRole("CTO");

        log.info("created an Employee --> {}", employee);
        employeeRepository.save(employee);

        assertThat(employee.getId()).isNotNull();
    }

    @Test
    void updateEmployeeRecordTest(){

        Employee employee = employeeRepository.findById(12).orElse(null);
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Bob");

        log.info("Employee before save --> {}", employee);
        employee.setFirstName("John");



        employeeRepository.save(employee);
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Dan");
        assertThat(employee.getRole()).isEqualTo("HR");

        log.info("Employee after save --> {}", employee);

    }
}