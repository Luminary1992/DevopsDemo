package com.javaback.ems_backend.controller;

import com.javaback.ems_backend.dto.EmployeeDto;
import com.javaback.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    //Build a Add employee Rest Api
    @PostMapping()
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);
        return new  ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }

    //BUILD GET EMPLOYEE REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeBuId(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto=employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employeeDto);
    }

    //build get all EMPLOYE REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employees=employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }

    //BUILD UPDATE EMPLOYEE REST API
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto employeeDto){
        EmployeeDto employee=employeeService.updateEmployee(employeeId,employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    //BUILD DELETE REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
         employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok("Employee is successfully deleted for the Id"+employeeId);
    }

}
