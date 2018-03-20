package com.edu.seu.dpf.bookstore.book.servlet.admin;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seu.dpf.bookstore.book.domain.Book;
import com.edu.seu.dpf.bookstore.book.service.BookService;
import com.edu.seu.dpf.bookstore.category.domain.Category;
import com.edu.seu.dpf.bookstore.category.service.CategoryService;

/**
 * Servlet implementation class AdminBookServlet
 */
@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("book", bookService.load(request.getParameter("bid")));
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
		
	}
	
	public String addPre(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		bookService.delete(bid);
		return findAll(request, response);
	}

	public String mod(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		
		bookService.mod(book);
		return findAll(request, response);
	}
}
