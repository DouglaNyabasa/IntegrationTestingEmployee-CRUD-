package com.code_With_Doug.Integration_Testing_Employee.CRUD;

import com.code_With_Doug.Integration_Testing_Employee.CRUD.dto.EmployeeRequestDTO;
import com.code_With_Doug.Integration_Testing_Employee.CRUD.model.Employee;
import org.hibernate.annotations.SQLInsert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {


	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private TestRepository testRepository;
	private static RestTemplate restTemplate;

	ApplicationTests(String baseUrl, TestRepository testRepository) {
		this.baseUrl = baseUrl;
		this.testRepository = testRepository;
	}

	@BeforeAll
	public  static  void  init(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void  setUp(){
		baseUrl=baseUrl.concat(":").concat(port+"").concat("/employee");
	}


	@Test
	public void testAddEmployee(){
		EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("douglas","nyabasa","douglasnyabasa400@gmail.com","01/20/1999","0784320423");
	     EmployeeRequestDTO response =	restTemplate.postForObject(baseUrl,employeeRequestDTO, EmployeeRequestDTO.class);
		assertEquals("douglas",response.getFirstname());
		assertEquals(1,testRepository.findAll().size());

	}
	@Disabled
	@Test
	@Sql(statements = "INSET INTO EMPLOYEE_TBL(id,firstName,lastName,email,dateOfBirth,phoneNumber)VALUES(2,naruto,uzumaki,narutouzumaki400@gmail.com,01/20/1999,0784320423)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM EMPLOYEE_TBL WHERE id=2",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testFindEmployeeById(){
		Employee employee = restTemplate.getForObject(baseUrl +"/{id}", Employee.class,2);

		assertAll(
				()->assertNotNull(employee),
				()->assertEquals("naruto",employee.getFirstname()),
				()->assertEquals(2,employee.getId()));
	}

	@Disabled
	@Test
	@Sql(statements = "INSET INTO EMPLOYEE_TBL(id,firstName,lastName,email,dateOfBirth,phoneNumber)VALUES(1,douglas,nyabasa,douglasnyabasa400@gmail.com,01/20/1999,0784320423)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM EMPLOYEE_TBL WHERE firstName='douglas'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public  void  testGetAllEmployees(){

		List<Employee> employees = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(1,employees.size());
		assertEquals(1,testRepository.findAll().size());
	}

	@Disabled
	@Test
	@Sql(statements = "INSET INTO EMPLOYEE_TBL(id,firstName,lastName,email,dateOfBirth,phoneNumber)VALUES(3,goku,kakarote,gokukakarote400@gmail.com,01/20/1999,0784320423)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM EMPLOYEE_TBL WHERE id=3",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEmployee(){
		Employee employee = new Employee(3L,"goku","black","01/20/1999","0784320423","gokukakarote400@gmail.com");
		restTemplate.put(baseUrl+"/update{id}",employee,3);
		Employee employee1 = testRepository.findById(3L).get();
		assertAll(
				()->assertNotNull(employee1),
				()->assertEquals("black",employee1.getLastName())
		);
	}





}
