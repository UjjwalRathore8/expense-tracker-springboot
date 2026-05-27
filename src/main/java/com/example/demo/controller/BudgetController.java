package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BudgetSummaryDto;
import com.example.demo.entity.Budget;
import com.example.demo.service.BudgetService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
	
	  @Autowired
	    private BudgetService budgetService;

	    @PostMapping
	    public ResponseEntity<Budget> addBudget(
	            @RequestBody Budget budget,
	            HttpServletRequest request) {

	        String email = (String) request.getAttribute("email");

	        Budget savedBudget = budgetService.addBudget(budget, email);

	        return ResponseEntity.ok(savedBudget);
	    }

	    @GetMapping
	    public ResponseEntity<List<Budget>> getAllBudgets(
	            HttpServletRequest request) {

	        String email = (String) request.getAttribute("email");

	        return ResponseEntity.ok(
	                budgetService.getAllBudgets(email));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Budget> getBudgetById(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                budgetService.getBudgetById(id));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Budget> updateBudget(
	            @PathVariable Long id,
	            @RequestBody Budget budget) {

	        return ResponseEntity.ok(
	                budgetService.updateBudget(id, budget));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteBudget(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                budgetService.deleteBudget(id));
	    }
	    
	    
	    @GetMapping("/summary")
	    public ResponseEntity<List<BudgetSummaryDto>> getBudgetSummary(
	            HttpServletRequest request) {

	        Long userId = (Long) request.getAttribute("userId");

	        return ResponseEntity.ok(budgetService.getBudgetSummary(userId));
	    }

}
