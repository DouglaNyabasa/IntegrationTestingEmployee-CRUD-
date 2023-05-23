package com.code_With_Doug.Integration_Testing_Employee.CRUD.repository;

import com.code_With_Doug.Integration_Testing_Employee.CRUD.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
