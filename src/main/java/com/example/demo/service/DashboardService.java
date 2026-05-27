package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Expense;
import com.example.demo.entity.Income;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.IncomeRepository;

@Service
public class DashboardService {

	@Autowired
	 private ExpenseRepository expenseRepository;

	    @Autowired
	    private IncomeRepository incomeRepository;

	    public Double getTotalExpense(String email) {
	        return expenseRepository.findByUserEmail(email)
	                .stream()
	                .mapToDouble(Expense::getAmount)
	                .sum();
	    }

	    public Double getTotalIncome(String email) {
	        return incomeRepository.findByUserEmail(email)
	                .stream()
	                .mapToDouble(Income::getAmount)
	                .sum();
	    }

	    public Double getBalance(String email) {
	        return getTotalIncome(email) - getTotalExpense(email);
	    }
	
}
