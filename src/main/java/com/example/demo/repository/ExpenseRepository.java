package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserEmail(String email);
    List<Expense> findByUserId(Long userId);
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.category.id = :categoryId")
    Double getTotalExpenseByCategoryId(@Param("categoryId") Long categoryId);
}