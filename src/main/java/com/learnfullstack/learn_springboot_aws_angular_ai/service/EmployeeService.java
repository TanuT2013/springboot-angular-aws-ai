package com.learnfullstack.learn_springboot_aws_angular_ai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnfullstack.learn_springboot_aws_angular_ai.pojo.Employee;
import com.learnfullstack.learn_springboot_aws_angular_ai.repository.EmployeeRepository;

@Service
public class EmployeeService {
	List<Employee> userList = new ArrayList<Employee>();
	@Autowired
	EmployeeRepository repository;

	public EmployeeService() {
	}

	public List<Employee> getEmployee() {
		return repository.findAll();
	}

	public boolean checkLoggedInEmployee(Employee loginuser) {
		return repository.findByNameAndPassword(loginuser.getName(), loginuser.getPassword()).isPresent();
	}

	public boolean registerUser(Employee employee, PasswordEncoder passEncoder) {
		if (repository.findByName(employee.getName()).isPresent()) {
			return false;
		}
		employee.setPassword(passEncoder.encode(employee.getPassword()));
		repository.save(employee);
		return true;
	}


	public boolean deleteEmployee(String name) {
		boolean checkDelete = false;
		Optional<Employee> emp = repository.findByName(name);
		if (emp.isPresent()) {
			repository.delete(emp.get());
			checkDelete = true;
		}
		return checkDelete;
	}

}
