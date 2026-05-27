package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DashboardService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	  @Autowired
	    private DashboardService dashboardService;

	    @GetMapping("/total-expense")
	    public ResponseEntity<Double> getTotalExpense(HttpServletRequest request) {
	        String email = (String) request.getAttribute("email");
	        return ResponseEntity.ok(dashboardService.getTotalExpense(email));
	    }

	    @GetMapping("/total-income")
	    public ResponseEntity<Double> getTotalIncome(HttpServletRequest request) {
	        String email = (String) request.getAttribute("email");
	        return ResponseEntity.ok(dashboardService.getTotalIncome(email));
	    }

	    @GetMapping("/balance")
	    public ResponseEntity<Double> getBalance(HttpServletRequest request) {
	        String email = (String) request.getAttribute("email");
	        return ResponseEntity.ok(dashboardService.getBalance(email));
	    }

}
