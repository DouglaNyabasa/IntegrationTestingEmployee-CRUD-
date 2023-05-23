package com.code_With_Doug.Integration_Testing_Employee.CRUD.controller;

import com.code_With_Doug.Integration_Testing_Employee.CRUD.dto.EmployeeRequestDTO;
import com.code_With_Doug.Integration_Testing_Employee.CRUD.dto.EmployeeResponseDTO;
import com.code_With_Doug.Integration_Testing_Employee.CRUD.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponseDTO> getAllEmployee(){
       return employeeService.getAllEmployee();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO){
       return employeeService.createEmployee(employeeRequestDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, EmployeeRequestDTO employeeRequestDTO){
       return employeeService.updateEmployee(id, employeeRequestDTO);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Response> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }

}
