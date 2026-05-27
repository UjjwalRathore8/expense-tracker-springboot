package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Expense;
import com.example.demo.service.ExpenseService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(
            @RequestBody Expense expense,
            HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        System.out.println("EMAIL FROM TOKEN = " + email);

        return ResponseEntity.ok(expenseService.addExpense(expense, email));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(
            HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return ResponseEntity.ok(expenseService.getAllExpenses(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getByExpenseId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateById(
            @RequestBody Expense expense,
            @PathVariable Long id) {

        return ResponseEntity.ok(expenseService.updateExpense(expense, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.deleteExpense(id));
    }
}