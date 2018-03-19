package com.edu.seu.dpf.bookstore.book.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.edu.seu.dpf.bookstore.book.domain.Book;
import com.edu.seu.dpf.bookstore.book.service.BookService;
import com.edu.seu.dpf.bookstore.category.domain.Category;
import com.edu.seu.dpf.bookstore.category.service.CategoryService;

import cn.itcast.utils.CommonUtils;

/**
 * Servlet implementation class AdminAddBookServlet
 */
@WebServlet("/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");//设置上传文件大小
		//把表单数据封装到Book对象中
		DiskFileItemFactory factory = new DiskFileItemFactory(200 * 1024, new File("F:/f/temp"));//创建工厂
		ServletFileUpload sfu = new ServletFileUpload(factory);//得到解析器
		sfu.setFileSizeMax(200 * 1024);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);//使用解析器去解析request对象
			/*
			 *把fileItemList中的数据封装到Book对象中
			 *把所有普通表单字段数据先封装到Map中
			 *再把map中的数据封装到Book对象中
			 */
			Map<String, String> map = new HashMap<>();
			for (FileItem fileItem : fileItemList) {
				if(fileItem.isFormField()) {
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			}
			Book book = CommonUtils.toBean(map, Book.class);
			book.setBid(CommonUtils.uuid());
			
			Category category = CommonUtils.toBean(map, Category.class);
			book.setCategory(category);
			/*
			 * 保存上传的文件，保存的路径和保存文件名称
			 */
			String savePath = this.getServletContext().getRealPath("/book_img");
			String filename = CommonUtils.uuid() + "_" + fileItemList.get(1).getName();
			
			//设置文件格式校验
			if(filename.toLowerCase().endsWith(".jpg")) {
				request.setAttribute("msg", "文件格式不是.jpg");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			File destFile = new File(savePath, filename);
			fileItemList.get(1).write(destFile);
			
			/*
			 * 设置Book对象的image
			 */
			book.setImage("book_img/" + filename);
			
			
			/*
			 * 使用bookSer完成保存
			 */
			bookService.add(book);
			
			//校验图片的尺寸
			Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getWidth(null) > 200 || image.getHeight(null) > 200) {
				destFile.delete();
				request.setAttribute("msg", "图片格式不正确!");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll");
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "文件超出大小200k");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
		}
		
	}

}
