package com.example.demo.dto;

public class BudgetSummaryDto {

	private Long id;
    private double limitAmount;
    private String month;
    private String categoryName;
    private double spent;
    private double remaining;
    private String status;

    public BudgetSummaryDto(Long id, double limitAmount, String month,
                            String categoryName, double spent,
                            double remaining, String status) {
        this.id = id;
        this.limitAmount = limitAmount;
        this.month = month;
        this.categoryName = categoryName;
        this.spent = spent;
        this.remaining = remaining;
        this.status = status;
    }

    public Long getId() { return id; }
    public double getLimitAmount() { return limitAmount; }
    public String getMonth() { return month; }
    public String getCategoryName() { return categoryName; }
    public double getSpent() { return spent; }
    public double getRemaining() { return remaining; }
    public String getStatus() { return status; }
	
}
