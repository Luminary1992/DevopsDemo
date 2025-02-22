package com.javaback.ems_backend.mapper;

import com.javaback.ems_backend.Entity.Employee;
import com.javaback.ems_backend.dto.EmployeeDto;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){

        return new Employee(
                employeeDto.getId() != null ? employeeDto.getId() : 0L,
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
