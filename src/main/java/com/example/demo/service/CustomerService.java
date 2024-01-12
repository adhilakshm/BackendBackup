package com.example.demo.service;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.CustomerDetails;
import com.example.demo.repo.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo repo;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);

    public int getActiveCount() {
        return repo.countActive();
    }

    public int getInactiveCount() {
        return repo.countInactive();
    }

    public Map<String, Map<String, Integer>> getActiveInactiveCustomerCountByMonth() {
        // Fetch the distinct months present in the database
        List<Integer> distinctMonths = repo.findDistinctMonths();

        // Sort the distinct months in ascending order
        distinctMonths.sort(Integer::compareTo);

        // Use DateFormatSymbols to get month names
        DateFormatSymbols dfs = new DateFormatSymbols();

        Map<String, Map<String, Integer>> customerCountsByMonth = new LinkedHashMap<>(); // Use LinkedHashMap to maintain insertion order

        for (Integer month : distinctMonths) {
            String startDate = String.format("%d-%02d-01", year, month);
            String endDate = String.format("%d-%02d-31", year, month);

            // Fetch customer details for the current month
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedStartDate = sdf.parse(startDate);
                java.util.Date parsedEndDate = sdf.parse(endDate);

                java.sql.Date sqlStartDate = new java.sql.Date(parsedStartDate.getTime());
                java.sql.Date sqlEndDate = new java.sql.Date(parsedEndDate.getTime());

                // Count active and inactive customers for the current month
                int activeCount = repo.countActiveByMonth(month);
                int inactiveCount = repo.countInactiveByMonth(month);

                // Prepare the result map for the current month
                Map<String, Integer> countsForMonth = new HashMap<>();
                countsForMonth.put("active", activeCount);
                countsForMonth.put("inactive", inactiveCount);

                // Store counts for the current month in the main map
                customerCountsByMonth.put(getMonthName(dfs.getMonths()[month - 1]), countsForMonth);
            } catch (Exception e) {
                // Handle the exception
                e.printStackTrace(); // Or log the error
            }
        }

        return customerCountsByMonth;
    }

    private String getMonthName(String month) {
        return month;
    }
}