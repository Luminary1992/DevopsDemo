package com.javaback.ems_backend.service.impl;

import com.javaback.ems_backend.Entity.Employee;
import com.javaback.ems_backend.dto.EmployeeDto;
import com.javaback.ems_backend.exception.ResourceNotFoundException;
import com.javaback.ems_backend.mapper.EmployeeMapper;
import com.javaback.ems_backend.repository.EmployeeRepository;
import com.javaback.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee=EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveEmployee=employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee is not exist for the given id"+ employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {

       List<Employee> employees =employeeRepository.findAll();
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee id does not exist for the give id"+employeeId));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee saveEmployee=employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee id does not exist for the give id"+employeeId));

        employeeRepository.deleteById(employeeId);
    }
}
