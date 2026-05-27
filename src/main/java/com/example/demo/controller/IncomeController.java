package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Income;
import com.example.demo.service.IncomeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<Income> addIncome(
            @RequestBody Income income,
            HttpServletRequest request) {

        Long userId =
                (Long) request.getAttribute("userId");

        return ResponseEntity.ok(
                incomeService.addIncome(income, userId)
        );
    }

    @GetMapping
    public ResponseEntity<List<Income>> getAllIncomes(
            HttpServletRequest request) {

        Long userId =
                (Long) request.getAttribute("userId");

        return ResponseEntity.ok(
                incomeService.getAllIncomes(userId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                incomeService.getIncomeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(
            @PathVariable Long id,
            @RequestBody Income income) {

        return ResponseEntity.ok(
                incomeService.updateIncome(id, income)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncome(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                incomeService.deleteIncome(id)
        );
    }
}