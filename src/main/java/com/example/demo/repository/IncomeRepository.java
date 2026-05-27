package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Income;

public interface IncomeRepository extends JpaRepository<Income , Long>{
	    
	    List<Income> findByUserEmail(String email);
	        List<Income> findByUserId(Long userId);
	    
}
