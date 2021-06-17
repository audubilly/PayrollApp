package com.example.payroll.service.util;

import com.example.payroll.data.Dto.EmployeeDto;
import com.example.payroll.data.model.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//map struct processor is used to generate the implementation of the map struct
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee);

}
