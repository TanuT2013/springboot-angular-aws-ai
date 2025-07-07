package com.learnfullstack.learn_springboot_aws_angular_ai.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learnfullstack.learn_springboot_aws_angular_ai.pojo.Employee;
import com.learnfullstack.learn_springboot_aws_angular_ai.repository.EmployeeRepository;

@Service
public class EmployeeDetailService implements UserDetailsService {
	@Autowired
	private EmployeeRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee employee = repository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Employee not found" + username));
		return new User(employee.getName(), employee.getPassword(), new ArrayList<>());
	}

}
