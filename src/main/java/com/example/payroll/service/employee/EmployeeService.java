package com.example.payroll.service.employee;

import com.example.payroll.data.Dto.EmployeeDto;
import com.example.payroll.data.model.Employee;
import com.example.payroll.web.exceptions.EmployeeDoesNotExistException;
import com.example.payroll.web.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    Employee save(EmployeeDto employeeDto);
    Employee findById(Integer id);
    List<Employee> findAll();
    void deleteById(Integer id) throws EmployeeDoesNotExistException;
    Employee update (EmployeeDto employeeDto, Integer id) throws EmployeeNotFoundException;
}
