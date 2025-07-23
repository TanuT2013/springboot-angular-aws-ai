package com.learnfullstack.learn_springboot_aws_angular_ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnfullstack.learn_springboot_aws_angular_ai.JWTUtil;
import com.learnfullstack.learn_springboot_aws_angular_ai.pojo.Employee;
import com.learnfullstack.learn_springboot_aws_angular_ai.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "User Management", description = "Operations related to employee registration, login, and retrieval")
public class LoginController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	AuthenticationManager authManager;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Operation(method = "GET", summary = "Get All Registered Users", description = "Returns the list of all registered employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved user list") })
	@GetMapping("/users")
	public List<Employee> getUsers() {
		return employeeService.getEmployee();
	}

	@Operation(summary = "Register a new user", description = "Registers a new employee in the system")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User registered successfully"),
			@ApiResponse(responseCode = "409", description = "User already exists") })
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Employee employee) {
		boolean created = employeeService.registerUser(employee, passwordEncoder);
		if (created) {
			return ResponseEntity.status(HttpStatus.CREATED).body("new user created" + employee.getName());
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("user already present");
		}
	}

	@Operation(summary = "Authenticate user and return JWT", description = "Validates user credentials and returns JWT token")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Authentication successful"),
			@ApiResponse(responseCode = "401", description = "Invalid credentials") })
	@PostMapping("/login")
	public ResponseEntity<String> checkLoggedInUser(@RequestBody Employee employee) {
		try {
			System.out.println("loggedinUser Details" + employee.getName());
			authManager
					.authenticate(new UsernamePasswordAuthenticationToken(employee.getName(), employee.getPassword()));
			String token = jwtUtil.generateToken(employee.getName());
			return ResponseEntity.ok("Bearer " + token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}

	}

	@Operation(summary = "Delete a user by name", description = "Deletes a user from the database by name")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User deleted successfully"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@DeleteMapping("/user/{name}")
	public ResponseEntity<String> deleteUser(@PathVariable String name) {
		boolean checkUser = employeeService.deleteEmployee(name);
		if (checkUser) {
			return ResponseEntity.status(HttpStatus.OK).body("Record deleted Successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No matching record found");

	}

}
