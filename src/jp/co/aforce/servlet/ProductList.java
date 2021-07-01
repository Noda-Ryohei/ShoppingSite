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

@WebServlet(urlPatterns = { "/list" })
public class ProductList extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//遷移先の設定
		String target = "views/user/list.jsp";

		//押されたボタンを取得
		String buttonName = request.getParameter("button-name");
		if (buttonName == null)
			buttonName = "";

		List<ProductBean> list = new ArrayList<>();
		ProductDAO dao = new ProductDAO();

		if (buttonName.isEmpty()) {

			//ボタンが押されてない（ほかのページからのアクセス）の場合、一覧を表示
			list = dao.searchByKeyword("");

		} else if (buttonName.equals("search")) {

			//検索
			//リクエストパラメータを受け取る
			String keyword = request.getParameter("keyword");
			if (keyword == null) {
				keyword = "";
			}
			list = dao.searchByKeyword(keyword);
			request.setAttribute("keyword", keyword);

		} else if (buttonName.equals("sort")) {

			//並べ替え
			String sort = request.getParameter("sort");
			if (sort != null && !sort.isEmpty()) {
				String[] sortArray = sort.split("-", 0);
				list = dao.sort(sortArray);
				request.setAttribute("sort", sort);
			} else {
				list = dao.searchByKeyword("");
			}
		}

		request.setAttribute("list", list);
		request.getRequestDispatcher(target).forward(request, response);

	}

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

}