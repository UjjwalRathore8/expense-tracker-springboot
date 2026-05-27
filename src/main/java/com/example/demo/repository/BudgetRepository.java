package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget , Long>
{
    List<Budget> findByUserEmail(String email);
    List<Budget> findByUserId(Long userId);
}
