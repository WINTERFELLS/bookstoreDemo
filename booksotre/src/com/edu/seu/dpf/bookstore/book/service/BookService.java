package com.edu.seu.dpf.bookstore.book.service;

import java.util.List;

import com.edu.seu.dpf.bookstore.book.dao.BookDao;
import com.edu.seu.dpf.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	
	public List<Book> findAll(){
		return bookDao.findAll();
	}
	
	public List<Book> findByCategory(String cid){
		return bookDao.findByCategory(cid);
	}
	
	public Book findBookByBid(String bid){
		return bookDao.findByBid(bid);
	}
}
