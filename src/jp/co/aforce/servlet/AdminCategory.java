package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/category")
public class AdminCategory extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		String target = "/views/admin/admin-category.jsp";
		request.getRequestDispatcher(target).forward(request, response);
				
	}
}
