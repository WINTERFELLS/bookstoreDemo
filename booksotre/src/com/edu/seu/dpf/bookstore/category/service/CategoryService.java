package com.edu.seu.dpf.bookstore.category.service;

import java.util.List;

import com.edu.seu.dpf.bookstore.book.dao.BookDao;
import com.edu.seu.dpf.bookstore.category.dao.CategoryDao;
import com.edu.seu.dpf.bookstore.category.domain.Category;
import com.edu.seu.dpf.bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	
	public List<Category>	findAll(){
		return categoryDao.findAll();
	}

	public void add(Category category) {
		categoryDao.add(category);		
	}

	public void delete(String cid) throws CategoryException {
		int count = bookDao.getCountByCid(cid);
		if(count > 0) {
			throw new CategoryException("该分类下还有图书，不能删除！");
		}else {
			categoryDao.delete(cid);
		}
	}

	public Category load(String cid) {
		
		return categoryDao.load(cid);
	}

	public void edit(Category category) {
		// TODO Auto-generated method stub
		categoryDao.edit(category);
	}
}
