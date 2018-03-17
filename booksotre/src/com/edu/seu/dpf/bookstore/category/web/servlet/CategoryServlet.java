package com.edu.seu.dpf.bookstore.category.web.servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seu.dpf.bookstore.category.service.CategoryService;

import cn.itcast.servlet.BaseServlet;

public class CategoryServlet extends BaseServlet{
	private CategoryService categoryService = new CategoryService();
	
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/jsps/left.jsp";
	}
}
