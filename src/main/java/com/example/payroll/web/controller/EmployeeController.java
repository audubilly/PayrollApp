package com.example.payroll.web.controller;

import com.example.payroll.data.Dto.EmployeeDto;
import com.example.payroll.data.model.Employee;
import com.example.payroll.service.employee.EmployeeService;
import com.example.payroll.web.exceptions.EmployeeDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resources;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping()
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @PostMapping()
    public Employee save(@Valid @RequestBody EmployeeDto employeeDto){
        return employeeService.save(employeeDto);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Integer id) throws EmployeeDoesNotExistException {
        return employeeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) throws EmployeeDoesNotExistException {
        employeeService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeDto employeeDto){

        return null;
    }
}
