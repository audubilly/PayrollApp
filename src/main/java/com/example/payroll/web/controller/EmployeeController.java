package com.example.payroll.web.controller;

import com.example.payroll.data.Dto.EmployeeDto;
import com.example.payroll.data.model.Employee;
import com.example.payroll.service.employee.EmployeeService;
import com.example.payroll.web.exceptions.EmployeeDoesNotExistException;
import com.example.payroll.web.exceptions.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resources;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Employee> employees = employeeService
                .findAll()
                .stream()
                .map(employee ->
                        employee.add(linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel(),
                                linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel())).collect(Collectors.toList());

        return ResponseEntity.ok().body(employees);
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.save(employeeDto);
        EntityModel<Employee> entityModel = EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        Employee employee = employeeService.findById(id);

        EntityModel<Employee> entityModel = EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));


        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) throws EmployeeDoesNotExistException {
        employeeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {


        Employee employee = null;
        EntityModel<Employee> entityModel = null;
        try {
            employee = employeeService.update(employeeDto, id);

             EntityModel.of(employee,
                    linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel(),
                    linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));


        } catch (EmployeeNotFoundException employeeNotFoundException) {
            log.info("Error occurred --> {}", employeeNotFoundException.getMessage());

            ResponseEntity.badRequest().body(employeeNotFoundException.getMessage());
        }
        return ResponseEntity.ok().body(entityModel);
    }
}