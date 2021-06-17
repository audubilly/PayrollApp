package com.example.payroll.data.Dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Data
public class EmployeeDto {

    @NotNull(message = "firstname must have a value")
    private String firstName;

    @NotNull(message = "lastname must have a value")
    private String lastName;

    @NotNull(message = "Role must not be null")
    private  String role;
}

