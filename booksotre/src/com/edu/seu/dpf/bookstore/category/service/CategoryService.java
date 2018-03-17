package com.edu.seu.dpf.bookstore.category.service;

import java.util.List;

import com.edu.seu.dpf.bookstore.category.dao.CategoryDao;
import com.edu.seu.dpf.bookstore.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	
	public List<Category>	findAll(){
		return categoryDao.findAll();
	}
}
