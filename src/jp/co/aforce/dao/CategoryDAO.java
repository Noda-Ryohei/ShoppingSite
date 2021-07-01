package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.CategoryBean;

public class CategoryDAO extends DAO {

	//全カテゴリの取得
	public List<CategoryBean> getAll() {
		
		List<CategoryBean> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			con = getConnection();
			String sql = "select * from category";
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				CategoryBean category = new CategoryBean();
				
				category.setId(rs.getString("id"));
				category.setName(rs.getString("name"));
				
				list.add(category);
				
			}
			
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}
