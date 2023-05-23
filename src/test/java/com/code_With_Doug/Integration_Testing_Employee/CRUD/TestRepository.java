package com.code_With_Doug.Integration_Testing_Employee.CRUD;

import com.code_With_Doug.Integration_Testing_Employee.CRUD.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Employee,Long> {


}
