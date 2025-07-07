package com.learnfullstack.learn_springboot_aws_angular_ai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnfullstack.learn_springboot_aws_angular_ai.pojo.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByNameAndPassword(String name, String password);

	Optional<Employee> findByName(String name);
}
