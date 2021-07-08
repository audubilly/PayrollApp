package com.example.payroll.service.employee;

import com.example.payroll.data.Dto.EmployeeDto;
import com.example.payroll.data.model.Employee;
import com.example.payroll.data.repository.EmployeeRepository;
import com.example.payroll.service.util.EmployeeMapper;
import com.example.payroll.service.util.EmployeeMapperImpl;
import com.example.payroll.web.exceptions.EmployeeDoesNotExistException;
import com.example.payroll.web.exceptions.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
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

    EmployeeMapper employeeMapper;

    public EmployeeServiceImpl() {
        employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    }

    @Override
    public Employee save(EmployeeDto employeeDto) {

        //Employye employee = modelMapper.map (employeeDto, Employee.class)
        Employee employee = new Employee();
        modelMapper.map(employeeDto, employee);

        log.info("Employee after mapping --> {}", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
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
    public Employee update(EmployeeDto employeeDto, Integer id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if(employee == null){
            throw new EmployeeNotFoundException("Employee not found");
        }

        employeeMapper.updateEmployeeFromDto(employeeDto,employee);
        log.info("Employee after mapping --> {}", employee);

        return employeeRepository.save(employee);
    }


}
