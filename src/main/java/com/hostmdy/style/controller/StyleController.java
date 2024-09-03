package com.hostmdy.style.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.style.model.Mode;
import com.hostmdy.style.model.Style;
import com.hostmdy.style.model.StyleDAO;
//import com.hostmdy.style.model.User;
import com.hostmdy.style.model.User;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSession;

@WebServlet("/style")
public class StyleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StyleDAO styleDAO;

	@Resource(name = "jdbc/platform")
	private DataSource dataSource;

	// private User user;

	@Override
	public void init() throws ServletException {
		styleDAO = new StyleDAO(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		user = (User) session.getAttribute("user");

		String param = req.getParameter("mode");
		Mode mode = null;
		if (param == null) {
			mode = Mode.LIST;
		} else {
			mode = Mode.valueOf(param);
		}

		switch (mode) {
//		case LIST:
//			showAllList(req, resp);
//			break;
		case LIKE:
			likeStyle(req, resp);
			break;
		case SINGLE:
			showStyle(req, resp);
			break;
		case STYLE_FORM:
			showNewStyleForm(req, resp);
			break;
		case CREATE:
			createStyle(req, resp);
			break;
		case LOAD:
			loadStyle(req, resp);
			break;
		case UPDATE:
			updateStyle(req, resp);
			break;
		case DELETE:
			deleteStyle(req, resp);
			break;
		case ALL_STYLES:
			showAllStyles(req, resp);
			break;
		case SEARCH:
			searchStyles(req, resp);
			break;
//		case FILTER:
//			filterStyles(req, resp);
//			break;
		default:
			showAllStyles(req, resp);
			break;
		}
	}

	private void likeStyle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			try {
				Long styleId = Long.parseLong(req.getParameter("styleId"));
				styleDAO.toggleLike(styleId, user.getId());

				// Update the style object in request scope after liking
				Style style = styleDAO.getStyleById(styleId);
				req.setAttribute("style", style);
			} catch (NumberFormatException e) {
				// Handle NumberFormatException
				e.printStackTrace(); // Log the exception
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid styleId parameter");
				return;
			}
		}

		// Redirect to the same page after liking
		resp.sendRedirect("style?mode=ALL_STYLES");
	}

	private void searchStyles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("query");
		System.out.println("Search query: " + query); // Log the query

		List<Style> styleList = styleDAO.filterStyles(query);

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		// Check if user is null
		if (user != null) {
			req.setAttribute("user", user);
			// System.out.println("User found: " + user.getUsername()); // Log the username
		} else {
			req.setAttribute("userError", "User not found");
			// System.out.println("User not found in session."); // Log user not found
		}

		req.setAttribute("styleList", styleList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/style/search.jsp");
		// System.out.println("Forwarding to template/style/style.jsp"); // Log before
		// forwarding
		dispatcher.forward(req, resp);
	}

	private void showAllStyles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Style> styleList = styleDAO.getAllStyles();
		req.setAttribute("styleList", styleList);
		// Retrieve user from session
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		// Check if user is null
		if (user != null) {
			req.setAttribute("user", user);
		} else {
			req.setAttribute("userError", "User not found");
		}

		if (user != null) {
			req.setAttribute("user", user);
			// Check if each style is liked by the user
			for (Style style : styleList) {
				boolean isLiked = styleDAO.isStyleLikedByUser(style.getId(), user.getId());
				style.setLiked(isLiked); // Assuming Style class has a `liked` property
			}
		} else {
			req.setAttribute("userError", "User not found");
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/style/style.jsp");
		dispatcher.forward(req, resp);
	}

	private void updateStyle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("styleId"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String image_url = req.getParameter("image_url");
		String category = req.getParameter("category");
		String youtubeLink = req.getParameter("youtubeLink"); // Ensure this matches your form input name

		Style style = new Style(id, name, description, image_url, category, null); // Initialize Style object

		// Set YouTube link
		style.setYoutubeLink(youtubeLink);

		// Update the style in the database
		boolean updateOk = styleDAO.updateStyle(style);

		if (updateOk) {
			resp.sendRedirect("style?mode=SINGLE&styleId=" + id);
		} else {
			req.setAttribute("updateOk", false);
			req.setAttribute("style", style);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/style/update-style.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private void createStyle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String image_url = req.getParameter("image_url");
		String category = req.getParameter("category");
		String youtubeLink = req.getParameter("youtubeLink");

		Style style = new Style(name, description, image_url, category, null, youtubeLink); // Initially, category_id is
																							// null
		boolean insertOk = styleDAO.createStyle(style);

		if (insertOk) {
			resp.sendRedirect("style?mode=ALL_STYLES");
		} else {
			req.setAttribute("insertOk", insertOk);
			showNewStyleForm(req, resp);
		}
	}

	private void showNewStyleForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			req.setAttribute("user", user);
		} else {
			req.setAttribute("userError", "User not found");
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/style/add-style.jsp");
		dispatcher.forward(req, resp);
	}

	private void showStyle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long styleId = Long.parseLong(req.getParameter("styleId"));
		Style style = styleDAO.getStyleById(styleId);
		req.setAttribute("style", style);
		// Retrieve user from session
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		// Check if user is null
		if (user != null) {
			req.setAttribute("user", user);
		} else {
			req.setAttribute("userError", "User not found");
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/style/style-details.jsp");
		dispatcher.forward(req, resp);
	}

	private void loadStyle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long styleId = Long.parseLong(req.getParameter("styleId"));
		Style style = styleDAO.getStyleById(styleId);
		req.setAttribute("style", style);
		// Retrieve user from session
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		// Check if user is null
		if (user != null) {
			req.setAttribute("user", user);
		} else {
			req.setAttribute("userError", "User not found");
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/style/update-style.jsp");
		dispatcher.forward(req, resp);
	}

	private void deleteStyle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long styleId = Long.parseLong(req.getParameter("styleId"));
		boolean deleteOk = styleDAO.deleteStyle(styleId);

		if (deleteOk) {
			resp.sendRedirect("style");
		} else {
			resp.sendRedirect("style?mode=SINGLE&styleId=" + styleId);// url change
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
