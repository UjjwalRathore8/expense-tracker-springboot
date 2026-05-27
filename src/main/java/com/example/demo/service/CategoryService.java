package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CategoryService {
	
	 @Autowired
	    private CategoryRepository categoryRepository;

	    @Autowired
	    private UserRepository userRepository;

	    public Category addCategory(Category category, String email) {
	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	        category.setUser(user);

	        return categoryRepository.save(category);
	    }

	    public List<Category> getAllCategories(Long userId) {
	        return categoryRepository.findByUserId(userId);
	    }

	    public Category getCategoryById(Long id) {
	        return categoryRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	    }

	    public Category updateCategory(Long id, Category category) {
	        Category existingCategory = getCategoryById(id);

	        existingCategory.setName(category.getName());

	        return categoryRepository.save(existingCategory);
	    }

	    public String deleteCategory(Long id) {
	        Category category = getCategoryById(id);

	        categoryRepository.delete(category);

	        return "Category deleted successfully";
	    }

}
