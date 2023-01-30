package com.training.springJPA_2_DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.training.springJPA_2_DB.model.employee.Employee;
import com.training.springJPA_2_DB.model.product.Product;
import com.training.springJPA_2_DB.service.CalculationService;

@SpringBootApplication
public class SpringJpa2DbApplication implements CommandLineRunner{

	@Autowired
	CalculationService calService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpa2DbApplication.class, args);
		
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		try {
		Employee emp = new Employee();
        emp.setName("John");
        emp.setEmail("john@test.com");
        emp.setAge(20);
        
        Product prod = new Product();
        prod.setName("prodName");
        prod.setPrice(20_000);
        
		calService.save(emp,prod);
		} catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("saved in DB");
		
	}

}
