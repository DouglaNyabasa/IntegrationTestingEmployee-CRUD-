package com.code_With_Doug.Integration_Testing_Employee.CRUD.service;

import com.code_With_Doug.Integration_Testing_Employee.CRUD.dto.EmployeeRequestDTO;
import com.code_With_Doug.Integration_Testing_Employee.CRUD.dto.EmployeeResponseDTO;
import com.code_With_Doug.Integration_Testing_Employee.CRUD.model.Employee;
import com.code_With_Doug.Integration_Testing_Employee.CRUD.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponseDTO> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::mapToEmployeeResponse).toList() ;
    }
    private EmployeeResponseDTO mapToEmployeeResponse(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .dateOfBirth(employee.getDateOfBirth())
                .email(employee.getEmail())
                .build();
    }
    public ResponseEntity createEmployee(EmployeeRequestDTO employeeRequestDTO){
       Employee employee = Employee.builder()
                .firstname(employeeRequestDTO.getFirstname())
                .lastName(employeeRequestDTO.getLastName())
                .dateOfBirth(employeeRequestDTO.getDateOfBirth())
                .email(employeeRequestDTO.getEmail())
                .phoneNumber(employeeRequestDTO.getPhoneNumber())
                .build();
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);

    }
    public ResponseEntity updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            Employee employee1 = employee.get();
            employee1.setFirstname(employeeRequestDTO.getFirstname());
            employee1.setLastName(employeeRequestDTO.getLastName());
            employee1.setDateOfBirth(employeeRequestDTO.getDateOfBirth());
            employee1.setEmail(employeeRequestDTO.getEmail());
            employee1.setPhoneNumber(employeeRequestDTO.getPhoneNumber());
            employeeRepository.save(employee1);
            return ResponseEntity.ok().body("Employee details has been updated");
        }
        else
            return ResponseEntity.ok().body("Employee Not Found");
    }
    public ResponseEntity deleteEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            employeeRepository.delete(employee.get());
            return ResponseEntity.ok().body("Employee successfully deleted");
        }
        else
            return ResponseEntity.ok().body("Employee Not Found");
    }
}
