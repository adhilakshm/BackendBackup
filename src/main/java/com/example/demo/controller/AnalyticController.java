package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AnalyticGraph;
import com.example.demo.repo.AnalyticRepo;

@RestController
public class AnalyticController {
	@Autowired
    private AnalyticRepo repo;
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    @GetMapping("/analytics")
    public Map<Integer, Long> getCustomerCountByYear() {
        List<AnalyticGraph> analyticGraphList = repo.findAll();
        Map<Integer, Long> customerCountByYear = new HashMap<>();

        for (AnalyticGraph analyticGraph : analyticGraphList) {
            Date billDate = analyticGraph.getBill_date();
            int year = ((java.sql.Date) billDate).toLocalDate().getYear();
            
            // Increment the customer count for that year
            customerCountByYear.put(year, customerCountByYear.getOrDefault(year, 0L) + 1);
        }

        return customerCountByYear;
    }

}
