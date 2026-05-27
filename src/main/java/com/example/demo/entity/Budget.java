package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double limitAmount;
	private String month;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getLimitAmount() {
		return limitAmount;
	}
	public void setLimitAmount(double limitAmount) {
		this.limitAmount = limitAmount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Budget(Long id, double limitAmount, String month, User user, Category category) {
		super();
		this.id = id;
		this.limitAmount = limitAmount;
		this.month = month;
		this.user = user;
		this.category = category;
	}
	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
