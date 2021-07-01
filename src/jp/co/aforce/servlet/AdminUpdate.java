package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

@WebServlet(urlPatterns = { "/admin/update" })
public class AdminUpdate extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("list").forward(request, response);

	}

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//遷移先の設定
		String target = "/views/admin/admin-detail.jsp";

		//商品番号を取得
		String id = request.getParameter("id");

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

		} else if (button.equals("update")) {
			//「変更」が押された場合、登録処理を実行。

			//リクエストパラメータを受け取る。
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String stock = request.getParameter("stock");
			String description = request.getParameter("description");
			String categoryId = request.getParameter("category-id");
			String fileName = request.getParameter("file-name");

			//ProductBeanの作成、値の格納。
			ProductBean product = new ProductBean();
			product.setId(id);
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
				if (dao.update(product)) {

					request.setAttribute("msg", "変更しました。");
					request.setAttribute("product", product);

				} else {

					request.setAttribute("msg", "変更に失敗しました");
					request.setAttribute("product", product);

				}
			}

		} else if (button.equals("delete")) {
			//「削除」が押された場合、削除処理を実行。
			ProductDAO dao = new ProductDAO();

			if (dao.delete(id)) {
				request.setAttribute("msg", "削除しました。");
				target = "list";
				response.sendRedirect(target);
				return;
				
			} else {
				request.setAttribute("msg", "削除に失敗しました。");
			}
		}

		//画面の遷移
		request.getRequestDispatcher(target).forward(request, response);

	}

}
