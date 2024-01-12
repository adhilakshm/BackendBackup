package com.example.demo.repo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.CustomerDetails;

public interface CustomerRepo extends JpaRepository<CustomerDetails, Integer> {
	
	
	 @Query("SELECT COUNT(c) FROM CustomerDetails c")
	    long countTotalSubscribers();
	

	@Query("SELECT c.bill, COUNT(c.bill) FROM CustomerDetails c GROUP BY c.bill")
	List<Object[]> getBillOccurrences();

//	@Query("SELECT COUNT(c) FROM CustomerDetails c WHERE c.customer_id = ?")
//	Long countDeletedColumns();
	
	@Query("SELECT SUM(c.bill_with_tax) FROM CustomerDetails c")
	long calculateTotalRevenue();


	@Query("SELECT c.bill_with_tax FROM CustomerDetails c")
    List<Double> getBillValues();
//
//	 @Query("SELECT COUNT(e) FROM CustomerDetails e WHERE e.status = 'Active'")
//	int getActiveCount();

//	 @Query("SELECT COUNT(e) FROM CustomerDetails e WHERE e.status = 'Inactive'")
//	int getInactiveCount();

	 @Query("SELECT COUNT(e) FROM CustomerDetails e WHERE e.status = 'Active'")
	int countActive();

	 @Query("SELECT COUNT(e) FROM CustomerDetails e WHERE e.status = 'Inactive'")
	int countInactive();


	  @Query("SELECT c.location, COUNT(c) FROM CustomerDetails c GROUP BY c.location")
	    List<Object[]> findProductLocations();
	

	    @Query("SELECT DISTINCT MONTH(c.dateOfIssue) FROM CustomerDetails c")
	    List<Integer> findDistinctMonths();

	    @Query("SELECT COUNT(c) FROM CustomerDetails c WHERE c.status = 'active' AND MONTH(c.dateOfIssue) = :month")
	    int countActiveByMonth(@Param("month") int month);

	    @Query("SELECT COUNT(c) FROM CustomerDetails c WHERE c.status = 'inactive' AND MONTH(c.dateOfIssue) = :month")
	    int countInactiveByMonth(@Param("month") int month);

	    // Convert java.util.Date to java.sql.Date for database query
	    @Query("SELECT c FROM CustomerDetails c WHERE c.dateOfIssue BETWEEN :startDate AND :endDate")
	    List<CustomerDetails> findByDateOfIssueBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	    
	    @Query("SELECT MONTH(c.dateOfIssue) as month, COUNT(c) FROM CustomerDetails c GROUP BY MONTH(c.dateOfIssue)")
	    List<Object[]> getBillOccurrencesByMonth();
	    

		  @Query("SELECT c.mode_of_plan, COUNT(c) FROM CustomerDetails c GROUP BY c.mode_of_plan")
		    List<Object[]> findProductPlans();
	}

	


	
	
	
	
	