package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	 @Autowired
	    private CategoryService categoryService;

	    @PostMapping
	    public ResponseEntity<Category> addCategory(
	            @RequestBody Category category,
	            HttpServletRequest request) {

	        String email = (String) request.getAttribute("email");

	        System.out.println("EMAIL FROM REQUEST = " + email);

	        Category savedCategory = categoryService.addCategory(category, email);

	        return ResponseEntity.ok(savedCategory);
	    }
	    @GetMapping
	    public ResponseEntity<List<Category>> getAllCategories(
	            HttpServletRequest request) {

	        Long userId = (Long) request.getAttribute("userId");

	        return ResponseEntity.ok(categoryService.getAllCategories(userId));
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	        return ResponseEntity.ok(categoryService.getCategoryById(id));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(
	            @PathVariable Long id,
	            @RequestBody Category category) {

	        return ResponseEntity.ok(categoryService.updateCategory(id, category));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
	        return ResponseEntity.ok(categoryService.deleteCategory(id));
	    }	
	
}
