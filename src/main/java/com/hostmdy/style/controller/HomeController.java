//package com.hostmdy.style.controller;
//
//import java.io.IOException;
//
//import javax.sql.DataSource;
//
//import com.hostmdy.style.model.Mode;
//import com.hostmdy.style.model.StyleDAO;
//
//import jakarta.annotation.Resource;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/home")
//public class HomeController extends HttpServlet {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private StyleDAO styleDAO;
//
//	@Resource(name = "jdbc/platform")
//	private DataSource dataSource;
//
//	@Override
//	public void init() throws ServletException {
//		styleDAO = new StyleDAO(dataSource);
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String param1 = req.getParameter("mode");
//		Mode mode = null;
//		if (param1 == null) {
//			mode = Mode.INDEX;
//		} else {
//			mode = Mode.valueOf(param1);
//		}
//
//		switch (mode) {
//		case INDEX:
//			showHomePage(req, resp);
//			break;
//
//		default:
//			showHomePage(req, resp);
//			break;
//
//		}
//	}
//
//	private void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher dispatcher = req.getRequestDispatcher("template/index.jsp");
//		dispatcher.forward(req, resp);
//
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGet(req, resp);
//	}
//
//}
