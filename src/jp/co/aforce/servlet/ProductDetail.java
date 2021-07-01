package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/detail")
public class ProductDetail extends HttpServlet {

	public void doGet (
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		doPost(request, response);
		
	}
	
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		//リクエストパラメータを受け取る
		String id = request.getParameter("product-id");
		
		//セッションの開始
		HttpSession session = request.getSession();
		
		//CartAddで個数選択せずにカートに入れるを押下したときは、セッションに入っている。
		if (id == null) {
			
			id = (String) session.getAttribute("product-id");
			session.removeAttribute("product-id");
			
			if (id == null) {
				response.sendRedirect("list");
				return;
			} else {
				request.setAttribute("msg", session.getAttribute("msg"));
				session.removeAttribute("msg");
			}
			
		}
		
		
		ProductDAO dao = new ProductDAO();
		ProductBean product = dao.searchById(id);
		
		session.setAttribute("product", product);
		
		//遷移先の設定
		String target = "views/user/detail.jsp";
		
		request.getRequestDispatcher(target).forward(request, response);
		
		
	}
	
}
