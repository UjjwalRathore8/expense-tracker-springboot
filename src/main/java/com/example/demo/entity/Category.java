package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Budget> budgets = new ArrayList<>();
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Expense> expenses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<Budget> getBudget() {
//        return budgets;
//    }
//
//    public void setBudget(List<Budget> budget) {
//        this.budgets = budget;
//    }
    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Category(Long id, String name, User user, List<Budget> budget, List<Expense> expenses) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.budgets = budget;
        this.expenses = expenses;
    }

    public Category() {
    }
}