package com.example.demo.dto;

public class DashboardResponseDto {

	private double totalExpense;
    private double totalIncome;
    private double balance;

    public DashboardResponseDto() {
    }

    public DashboardResponseDto(double totalExpense, double totalIncome, double balance) {
        this.totalExpense = totalExpense;
        this.totalIncome = totalIncome;
        this.balance = balance;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getBalance() {
        return balance;
    }
	
}
