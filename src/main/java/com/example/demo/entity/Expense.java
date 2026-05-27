package com.example.demo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title is required")
	private String title;

	@Min(value = 1, message = "Amount must be greater than 0")
	private int amount;

	private String description;

	@NotNull(message = "Date is required")
	private LocalDate date;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

	public Expense(Long id, String title, int amount,
			String description, LocalDate date,
			User user, Category category) {

		super();

		this.id = id;
		this.title = title;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.user = user;
		this.category = category;
	}

	public Expense() {
		super();
	}
}