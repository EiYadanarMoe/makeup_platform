package com.hostmdy.style.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.style.model.Style;
import com.hostmdy.style.model.StyleDAO;
import com.hostmdy.style.model.User;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/likedStyles")
public class LikeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StyleDAO styleDAO;
	private User user;
	@Resource(name = "jdbc/platform")
	private DataSource dataSource;

    @Override
    public void init() {
        styleDAO = new StyleDAO(dataSource);
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession();
	        User user = (User) session.getAttribute("user");

	        if (user != null) {
	            List<Style> likedStyles = styleDAO.getLikedStylesByUserId(user.getId());
	            req.setAttribute("likedStyles", likedStyles);
	            RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/liked.jsp");
	            dispatcher.forward(req, resp);
	        } else {
	            resp.sendRedirect("template/user/sign-up.jsp"); 
	        }
	    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
