package com.example.payroll.web.exceptions;

public class EmployeeDoesNotExistException extends Exception{
    public EmployeeDoesNotExistException() {
    }

    public EmployeeDoesNotExistException(String message) {
        super(message);
    }

    public EmployeeDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
