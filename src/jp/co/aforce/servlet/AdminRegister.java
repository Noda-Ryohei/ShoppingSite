package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

@WebServlet(urlPatterns = { "/admin/register" })
public class AdminRegister extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//遷移先の設定
		String target = "/views/admin/admin-register.jsp";
		request.getRequestDispatcher(target).forward(request, response);
		
	}

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//遷移先の設定
		String target = "/views/admin/admin-register.jsp";

		//押されたボタンの取得
		String button = request.getParameter("button-name");
		if (button == null) {
			button = "";
		}

		if (button.isEmpty()) {
			//空の場合、特に何もしない。

		} else if (button.equals("back")) {
			//「戻る」が押された場合、商品一覧に遷移する。

			target = "list";
			response.sendRedirect(target);
			return;

		} else if (button.equals("register")) {
			//「登録」が押された場合、登録処理を実行。

			//リクエストパラメータを受け取る
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String stock = request.getParameter("stock");
			String description = request.getParameter("description");
			String categoryId = request.getParameter("category-id");
			String fileName = request.getParameter("file-name");

			//ProductBeanの作成、値の格納
			ProductBean product = new ProductBean();
			product.setName(name);
			product.setPrice(price);
			product.setStock(stock);
			product.setDescription(description);
			product.setCategoryId(categoryId);
			product.setFileName(fileName);

			//DAOの実行
			ProductDAO dao = new ProductDAO();

			//入力値チェック
			if (!dao.inputCheck(product)) {

				//未入力がある場合
				request.setAttribute("msg", "入力されていない項目があります。");
				request.setAttribute("product", product);

			} else {

				//登録処理
				if (dao.insert(product)) {

					request.setAttribute("msg", "「" + product.getName() + "」を追加しました");

				} else {

					request.setAttribute("msg", "追加に失敗しました");
					request.setAttribute("product", product);

				}

			}

		}

		//画面の遷移
		request.getRequestDispatcher(target).forward(request, response);

	}

}
