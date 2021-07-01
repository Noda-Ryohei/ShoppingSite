package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.PaymentBean;

public class PaymentDAO extends DAO {

	//全カテゴリの取得
	public List<PaymentBean> getAll() {
		
		List<PaymentBean> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			con = getConnection();
			String sql = "select * from payment";
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				PaymentBean payment = new PaymentBean();
				
				payment.setId(rs.getString("id"));
				payment.setName(rs.getString("name"));
				
				list.add(payment);
				
			}
			
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}
