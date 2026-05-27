package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BudgetSummaryDto;
import com.example.demo.entity.Budget;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BudgetService {


    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ExpenseRepository expenseRepository;

    public Budget addBudget(Budget budget, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        budget.setUser(user);

        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets(String email) {

        return budgetRepository.findByUserEmail(email);
    }

    public Budget getBudgetById(Long id) {

        return budgetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));
    }
    public Budget updateBudget(Long id, Budget budget) {

        Budget existingBudget = getBudgetById(id);

        existingBudget.setLimitAmount(budget.getLimitAmount());
        existingBudget.setMonth(budget.getMonth());

        return budgetRepository.save(existingBudget);
    }

    public String deleteBudget(Long id) {

        Budget budget = getBudgetById(id);

        budgetRepository.delete(budget);

        return "Budget deleted successfully";
    }
	
    
//     expense tracker
    public List<BudgetSummaryDto> getBudgetSummary(Long userId) {

        List<Budget> budgets = budgetRepository.findByUserId(userId);

        return budgets.stream().map(budget -> {

            Long categoryId = budget.getCategory().getId();

            double spent =
                    expenseRepository.getTotalExpenseByCategoryId(categoryId);

            double remaining =
                    budget.getLimitAmount() - spent;

            String status;

            if (remaining < 0) {
                status = "Exceeded";
            } else if (spent >= budget.getLimitAmount() * 0.8) {
                status = "Near Limit";
            } else {
                status = "Safe";
            }

            return new BudgetSummaryDto(
                    budget.getId(),
                    budget.getLimitAmount(),
                    budget.getMonth(),
                    budget.getCategory().getName(),
                    spent,
                    remaining,
                    status
            );

        }).toList();
    }
	
}
