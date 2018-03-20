package com.edu.seu.dpf.bookstore.user.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.edu.seu.dpf.bookstore.user.domain.User;

@WebFilter(
		urlPatterns = { 
				"/jsps/cart/*", 
				"/jsps/order/*"
		}, 
		servletNames = { 
				"CartServlet", 
				"OrderServlet"
		})
public class LoginFilter implements Filter {


    public LoginFilter() {
    	
    }


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 从Session中获取用户信息
		 * 判断Session中是否存在用户信息
		 */
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("session_user");
		if(user != null) {
			chain.doFilter(request, response);
		}else {
			httpRequest.setAttribute("msg", "请登录");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
