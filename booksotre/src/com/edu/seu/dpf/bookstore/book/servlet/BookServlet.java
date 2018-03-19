package com.edu.seu.dpf.bookstore.book.servlet;

import cn.itcast.servlet.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seu.dpf.bookstore.book.service.BookService;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	BookService bookService = new BookService();
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}
	
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("bookList", bookService.findByCategory(cid));
		return "f:/jsps/book/list.jsp";
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("bookList", bookService.load(request.getParameter("bid")));
		return "f:/jsps/book/desc.jsp";
	}
}
