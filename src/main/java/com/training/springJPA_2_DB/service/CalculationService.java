package com.training.springJPA_2_DB.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.springJPA_2_DB.dao.employee.repo.EmployeeRepository;
import com.training.springJPA_2_DB.dao.product.repo.ProductRepository;
import com.training.springJPA_2_DB.model.employee.Employee;
import com.training.springJPA_2_DB.model.product.Product;

@Service
public class CalculationService {

	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public void save(Employee emp, Product prod) {
	    empRepository.save(emp);
	    int i =10/0;
	    productRepository.save(prod);
	}
}
