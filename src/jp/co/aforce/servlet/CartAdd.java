package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.ProductDAO;

@WebServlet(urlPatterns = { "/cart-add" })
public class CartAdd extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

	@SuppressWarnings("unchecked")
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションの取得、または開始
		HttpSession session = request.getSession();

		//遷移先の設定
		String target = "cart";

		//セッションから、カート（商品ビーンとその個数のマップ）を取得。nullの場合は新しくカートを生成してセッションに追加。
		Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<ProductBean, Integer>();
			session.setAttribute("cart", cart);
		}

		//ボタンが押されたか確認（
		String button = request.getParameter("button-name");
		if (button == null) {
			button = "";
		}

		if ("".equals(button)) { //ヨーダ記法というよーだ。

		} else if (button.equals("add")) {

			//商品番号を取得と、追加する個数を取得
			String id = request.getParameter("product-id");
			String countStr = request.getParameter("count");

			if (countStr == null || countStr.isEmpty()) {
				session.setAttribute("msg", "個数を選択してください。");
				session.setAttribute("product-id", id);
				target="detail";
			} else {
				int count = Integer.parseInt(request.getParameter("count"));

				//カートに追加する処理
				boolean existInCart = false;

				//カートの中を探してすでに商品があれば、それらを増やす。
				for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {
					if (entry.getKey().getId().equals(id)) {
						ProductBean p = entry.getKey();
						cart.put(p, cart.get(p) + count);
						existInCart = true;
					}
				}

				//カートの中になければ、商品ビーンを作成して追加
				if (!existInCart) {
					ProductBean product = new ProductBean();
					ProductDAO dao = new ProductDAO();
					product = dao.searchById(id);
					cart.put(product, count);
				}
			}
		}

//		//合計金額の計算処理
//		int total = 0;
//
//		for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {
//
//			int price = Integer.parseInt(entry.getKey().getPrice());
//			int count = entry.getValue();
//			total += price * count;
//
//		}
//		session.setAttribute("total", Integer.toString(total));

		response.sendRedirect(target);

	}
}
