package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Income;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IncomeRepository;
import com.example.demo.repository.UserRepository;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    public Income addIncome(
            Income income,
            Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        income.setUser(user);

        return incomeRepository.save(income);
    }

    public List<Income> getAllIncomes(
            Long userId) {

        return incomeRepository.findByUserId(userId);
    }

    public Income getIncomeById(Long id) {

        return incomeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Income not found"));
    }

    public Income updateIncome(
            Long id,
            Income income) {

        Income existingIncome =
                getIncomeById(id);

        existingIncome.setSource(
                income.getSource());

        existingIncome.setAmount(
                income.getAmount());

        existingIncome.setDate(
                income.getDate());

        return incomeRepository.save(existingIncome);
    }

    public String deleteIncome(Long id) {

        Income income =
                getIncomeById(id);

        incomeRepository.delete(income);

        return "Income deleted successfully";
    }
}