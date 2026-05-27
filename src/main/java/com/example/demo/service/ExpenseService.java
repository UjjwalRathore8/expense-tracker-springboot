package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Expense addExpense(Expense expense, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Long categoryId = expense.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        expense.setUser(user);
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return expenseRepository.findByUserId(user.getId());
    }

    public Expense getByExpenseId(Long id) {

        return expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No Expense Record Found"));
    }

    public Expense updateExpense(Expense expense, Long id) {

        Expense existingExpense = getByExpenseId(id);

        existingExpense.setTitle(expense.getTitle());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setDate(expense.getDate());

        if (expense.getCategory() != null &&
            expense.getCategory().getId() != null) {

            Category category =
                    categoryRepository.findById(
                            expense.getCategory().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Category not found"));

            existingExpense.setCategory(category);
        }

        return expenseRepository.save(existingExpense);
    }

    public String deleteExpense(Long id) {

        Expense existingExpense = getByExpenseId(id);

        expenseRepository.delete(existingExpense);

        return "Expense Deleted Successfully";
    }
}