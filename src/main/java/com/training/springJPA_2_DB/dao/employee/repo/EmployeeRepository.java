package com.training.springJPA_2_DB.dao.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.springJPA_2_DB.model.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}