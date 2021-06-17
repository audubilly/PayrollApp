package com.example.payroll.service.employee;

import com.example.payroll.data.Dto.EmployeeDto;
import com.example.payroll.data.model.Employee;
import com.example.payroll.data.repository.EmployeeRepository;
import com.example.payroll.web.exceptions.EmployeeDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Employee save(EmployeeDto employeeDto) {

        //Employye employee = modelMapper.map (employeeDto, Employee.class)
        Employee employee = new Employee();
        modelMapper.map(employeeDto,Employee.class);

        log.info("Employee after mapping --> {}", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Integer id) throws EmployeeDoesNotExistException {
        if (id == null) throw new NullPointerException("Employee id cannot be null");
        return employeeRepository.findById(id).orElseThrow(()-> new EmployeeDoesNotExistException("Employee with this id does not exist"));

    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) throws EmployeeDoesNotExistException {
        if (id == null) {
            throw new NullPointerException("Employee id cannot be null");
        }
        Employee employee = findById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public Employee update(EmployeeDto employeeDto) {
        return null;
    }


}
