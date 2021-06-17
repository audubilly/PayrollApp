package com.example.payroll.service.employee;

import com.example.payroll.data.Dto.EmployeeDto;
import com.example.payroll.data.model.Employee;
import com.example.payroll.data.repository.EmployeeRepository;
import com.example.payroll.web.exceptions.EmployeeDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Sql(scripts = "classpath:db/insert.sql")
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void save() {
        when(employeeServiceImpl.save(new EmployeeDto())).thenReturn(employee);
        employeeServiceImpl.save(new EmployeeDto());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void findById() throws EmployeeDoesNotExistException {
//        Integer id = 1;

        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        employeeServiceImpl.findById(anyInt());
        verify(employeeRepository, times(1)).findById(anyInt());
    }

    @Test
    void findAll() {
        List<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        employeeServiceImpl.findAll();
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void deleteById() throws EmployeeDoesNotExistException {

        doNothing().when(employeeRepository).delete(any());
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        employeeServiceImpl.deleteById(anyInt());
        verify(employeeRepository, times(1))
                .delete(any());

    }

    }
