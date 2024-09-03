//package com.hostmdy.style.controller;
//
//import java.io.IOException;
//
//import javax.sql.DataSource;
//
//import com.hostmdy.style.model.User;
//import com.hostmdy.style.model.UserDAO;
//
//import jakarta.annotation.Resource;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebServlet("/profile")
//public class ProfileController extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//    private UserDAO userDao;
//    @Resource(name = "jdbc/platform")
//    private DataSource dataSource;
//
//    @Override
//    public void init() {
//        userDao = new UserDAO(dataSource);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session != null) {
//            User user = (User) session.getAttribute("user");
//            if (user != null) {
//                req.setAttribute("user", user);
//                RequestDispatcher dispatcher = req.getRequestDispatcher("/template/user/profile.jsp");
//                dispatcher.forward(req, resp);
//            } else {
//                resp.sendRedirect("login.jsp");
//            }
//        } else {
//            resp.sendRedirect("login.jsp");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session != null) {
//            User user = (User) session.getAttribute("user");
//            if (user != null) {
//                String firstname = req.getParameter("firstname");
//                String lastname = req.getParameter("lastname");
//                String email = req.getParameter("email");
//
//                user.setFirstname(firstname);
//                user.setLastname(lastname);
//                user.setEmail(email);
//
//                userDao.updateUser(user);
//
//                session.setAttribute("user", user);
//                req.setAttribute("user", user);
//                req.setAttribute("updateSuccess", "Profile updated successfully");
//
//                RequestDispatcher dispatcher = req.getRequestDispatcher("/template/user/profile.jsp");
//                dispatcher.forward(req, resp);
//            } else {
//                resp.sendRedirect("login.jsp");
//            }
//        } else {
//            resp.sendRedirect("login.jsp");
//        }
//    }
//}
