package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

@WebServlet(urlPatterns= {"/admin/detail"})
public class AdminDetail extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		String target = "list";
		response.sendRedirect(target);
		
	}
	
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		//遷移先の設定
		String target = "list";
		
		//商品番号の取得
		String id = request.getParameter("product-id");
		
		if (id == null) {
			id = "";
		}
		
		if (id.isEmpty()) {
			
		} else {
			ProductDAO dao = new ProductDAO();
			ProductBean product = dao.searchById(id);
			
			if (product == null) {
				request.setAttribute("msg", "商品が見つかりません。");
			} else {
				request.setAttribute("product", product);
				target = "/views/admin/admin-detail.jsp";
				request.getRequestDispatcher(target).forward(request, response);
				return;
			}
		}

		response.sendRedirect(target);
		
	}
	
}
