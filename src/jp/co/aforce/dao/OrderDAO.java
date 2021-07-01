package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import jp.co.aforce.beans.OrderBean;
import jp.co.aforce.beans.ProductBean;

public class OrderDAO extends DAO {
	
	//入力値チェック
	public boolean inputCheck(OrderBean order) {
		
		if (order.getCart() == null
				|| order.getCart().size() == 0
				|| order.getId().isEmpty()
				|| order.getDate().isEmpty()
				|| order.getMemberId().isEmpty()
				|| order.getDeliveryName().isEmpty()
				|| order.getPostalCode().isEmpty()
				|| order.getDeliveryAddress().isEmpty()
				|| order.getPaymentId().isEmpty()
				) {
			return false;
		}
		
		//郵便番号は7桁の整数。
		if (order.getPostalCode().length() != 7) {
			return false;
		}
		try {
			Integer.parseInt(order.getPostalCode());
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
		
	}

	//注文を追加
	public boolean insert(OrderBean order) {
		
		boolean result = true; //真で初期化。うまくいかなかったら偽を代入。最後まで生き残ったら真。（真での初期化はあまりよろしくなさそう。）
		Connection con = null;
		PreparedStatement st = null;
		
		try {

			con = getConnection();
			con.setAutoCommit(false);
			
			//注文番号生成  DAOの外でも使いたかったので、外側で処理することにする。
//			LocalDateTime datetime = LocalDateTime.now();
//			DateTimeFormatter format = 
//					DateTimeFormatter.ofPattern("yyMMddHHmmss");
//			String id = "X" + format.format(datetime);
//			format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			String date = format.format(datetime);
			
			
			//salesテーブルへの挿入の実行。
			st = con.prepareStatement(
					"insert into sales value(?, ?, ?, ?, ?, ?, ?)"
					);
			st.setString(1, order.getId());
			st.setString(2, order.getDate());
			st.setString(3, order.getMemberId());
			st.setString(4, order.getDeliveryName());
			st.setInt(5,Integer.parseInt(order.getPostalCode()));
			st.setString(6, order.getDeliveryAddress());
			st.setString(7, order.getPaymentId());
			
			int line = st.executeUpdate();
			st.close();
			
			
			if (line != 1) {
			
				//selesテーブルへの挿入が失敗したとき、ロールバックしてコネクションを閉じる。
				con.rollback();
				con.setAutoCommit(true);
				con.close();
				result = false;
				
			} else {
				//salesテーブルへの挿入が成功したとき、
				
				//続いてsales_detailテーブルへの挿入の実行。
				
				for ( Map.Entry<ProductBean, Integer> entry : order.getCart().entrySet() ) {
					
					st = con.prepareStatement(
							"insert into sales_detail value(?, ?, ?)"
							);
					st.setString(1, order.getId());
					st.setString(2, entry.getKey().getId());
					st.setInt(3, entry.getValue());
					
					line = st.executeUpdate();
					st.close();
					
					if (line != 1) {
						//sales_detailテーブルへの挿入が失敗したとき、
						
						//ロールバックしてコネクションを閉じる。
						con.rollback();
						con.setAutoCommit(true);
						con.close();
						result = false;
						break;
						
					} else {
						//sales_detailテーブルへの挿入が成功したとき、
						
						//引き続き、次の商品の挿入の実行。
						continue;
						
					}
					
				}
				
			}
			
			if (result) {
				con.commit();
				con.setAutoCommit(true);
				con.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}