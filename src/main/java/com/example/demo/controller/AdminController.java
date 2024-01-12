package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.CustomerDetails;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.service.CustomerService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Controller
public class AdminController {
	
	@Autowired
	CustomerRepo repo;
	@Autowired
	CustomerService service;
	 @CrossOrigin(origins = "http://localhost:4200") 
	    @ResponseBody
	

	    @GetMapping("/getCustomers")
	    public List<CustomerDetails> getCustomers() {
	        return repo.findAll();
	    }


	

	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/getBillOccurrences")
	    public List<Object[]> getBillOccurrences() {
	        return repo.getBillOccurrences();
	    }
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/getTotalSubscribers")
	    public Long getTotalSubscribers() {
	        return repo.countTotalSubscribers(); // Count the total number of subscribers
	    }
//	    @CrossOrigin(origins = "http://localhost:4200")
//	    @ResponseBody
//	    @GetMapping("/getDeletedColumnsCount")
//	    public Long getDeletedColumnsCount() {
//	        return repo.countDeletedColumns();
//	    }
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/getTotalRevenue")
	    public Long getTotalRevenue() {
	        return repo.calculateTotalRevenue(); // Calculate total revenue
	    }
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/calculateAverageBill")
	    public Double calculateAverageBill() {
	        List<Double> bills = repo.getBillValues();
	        OptionalDouble average = bills.stream().mapToDouble(Double::doubleValue).average();
	        return average.orElse(0.0);
	    }
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/getActiveCount")
	    public ResponseEntity<Integer> getActiveCount() {
	    	int activeCount = service.getActiveCount();
	        return ResponseEntity.ok(activeCount);
	    }
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/getInactiveCount")
	    public ResponseEntity<Integer> getInactiveCount() {
	        int inactiveCount = service.getInactiveCount();
	        return ResponseEntity.ok(inactiveCount);
	    }
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/getProductLocations")
	    public List<Object[]> getProductLocations() {
	        return repo.findProductLocations();
	    }
	    
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/countsByMonth")
	    public Map<String, Map<String, Integer>> getCustomerCountsByMonth() {
	        return service.getActiveInactiveCustomerCountByMonth();
	    }
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/getBillOccurrencesByMonth")
	    public List<Object[]> getBillOccurrencesByMonth() {
	        return repo.getBillOccurrencesByMonth();
	    }
	    @CrossOrigin(origins = "http://localhost:4200")
	    @ResponseBody
	    @GetMapping("/plans")
	    public List<Object[]> findProductPlans() {
	        return repo.findProductPlans();
	    }
	    
	}

	
	
	