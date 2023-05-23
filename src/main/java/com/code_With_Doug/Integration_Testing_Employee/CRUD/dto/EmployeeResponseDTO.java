package com.code_With_Doug.Integration_Testing_Employee.CRUD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {

    private  Long id;
    private  String firstname;
    private  String lastName;
    private  String email;

    private  String dateOfBirth;
    private  String phoneNumber;


}
