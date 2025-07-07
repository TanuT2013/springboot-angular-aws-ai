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

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	AuthenticationManager authManager;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/users")
	public List<Employee> getUsers() {
		return employeeService.getEmployee();
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Employee employee) {
		boolean created = employeeService.registerUser(employee, passwordEncoder);
		if (created) {
			return ResponseEntity.status(HttpStatus.CREATED)
				.body("new user created" + employee.getName());
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("user already present");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> checkLoggedInUser(@RequestBody Employee employee) {
		try {
			authManager
					.authenticate(new UsernamePasswordAuthenticationToken(employee.getName(), employee.getPassword()));
			String token = jwtUtil.generateToken(employee.getName());
			return ResponseEntity.ok("Bearer " + token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}

	}

	@DeleteMapping("/user/{name}")
	public ResponseEntity<String> deleteUser(@PathVariable String name) {
		boolean checkUser = employeeService.deleteEmployee(name);
		if (checkUser) {
			return ResponseEntity.status(HttpStatus.OK).body("Record deleted Successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No matching record found");

	}

}
