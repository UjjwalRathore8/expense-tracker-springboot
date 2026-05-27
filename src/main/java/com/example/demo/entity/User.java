package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Enter valid email")
	@Column(unique = true, nullable = false)
	private String email;
	@NotBlank(message = "Password is required")
	private String password;
//	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Expense> expenses = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
	private List<Category> categories = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy= "user" , cascade = CascadeType.ALL)
	private List<Income> incomes = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy= "user" , cascade = CascadeType.ALL)
	private List<Budget> budgets = new ArrayList<>();

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
//	@JsonIgnore
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
//	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonIgnore
	public List<Expense> getExpense() {
		return expenses;
	}
	
	public void setExpense(List<Expense> expense) {
		this.expenses = expense;
	}
	@JsonIgnore
	public List<Category> getCategory() {
		return categories;
	}

	public void setCategory(List<Category> category) {
		this.categories = category;
	}
	@JsonIgnore
	public List<Income> getIncome() {
		return incomes;
	}

	public void setIncome(List<Income> income) {
		this.incomes = income;
	}
	@JsonIgnore
	public List<Budget> getBudget() {
		return budgets;
	}

	public void setBudget(List<Budget> budget) {
		this.budgets = budget;
	}

	public User(Long id, String name, String email, String password, List<Expense> expense, List<Category> category,
			List<Income> income, List<Budget> budget) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.expenses = expense;
		this.categories = category;
		this.incomes = income;
		this.budgets = budget;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
