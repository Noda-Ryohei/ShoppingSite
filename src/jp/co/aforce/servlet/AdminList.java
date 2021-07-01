package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/admin/list")
public class AdminList extends HttpServlet {

	public void doGet (
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		List<ProductBean> list = new ArrayList<>();
		ProductDAO dao = new ProductDAO();
		list = dao.searchByKeyword("");
		request.setAttribute("list", list);
		String target = "/views/admin/admin-list.jsp";
		request.getRequestDispatcher(target).forward(request,response);
		
	}
	
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		//遷移先の設定
		String target = "/views/admin/admin-list.jsp";
		
		//押されたボタンの取得
		String button = request.getParameter("button-name");
		if (button == null) {
			button ="";
		}
		
		List<ProductBean> list = new ArrayList<>();
		ProductDAO dao = new ProductDAO();
		
		
		if (button.isEmpty() || button.equals("back")) {
			
			//空もしくは「戻る」の場合、特に何もしない。
			list = dao.searchByKeyword("");
			
		} else if (button.equals("search")) {
			
			//「検索」が押された時の処理。
			String keyword = request.getParameter("keyword");
			if (keyword == null) {
				keyword = "";
			}
			list = dao.searchByKeyword(keyword);
			request.setAttribute("keyword", keyword);
			
		} else if (button.equals("sort")) {
			
			//「並べ替え」が押された時の処理。
			String sort = request.getParameter("sort");
			if (sort != null && !sort.isEmpty()) {
				String[] sortArray = sort.split("-", 0);
				list = dao.sort(sortArray);
				request.setAttribute("sort", sort);
			} else {
				list = dao.searchByKeyword("");
			}
			
		} else if (button.equals("reload")) {
			//「更新」が押された時の処理。
			
			list = dao.searchByKeyword("");
			
		} else if (button.equals("register")) {
			//「追加」が押された時の処理。
			//商品追加画面に遷移。
			
			target = "register";
			response.sendRedirect(target);
			return;
			
		} else {

			list = dao.searchByKeyword("");
		}

		request.setAttribute("list", list);
		request.getRequestDispatcher(target).forward(request, response);
		
	}
	
	
}
