package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.ProductBean;

public class ProductDAO extends DAO {
	
	//入力値チェック
	public boolean inputCheck(ProductBean product) {
		
		if (product.getName().isEmpty() 
				|| product.getPrice().isEmpty() 
				|| product.getStock().isEmpty() 
				|| product.getCategoryId().isEmpty() 
				) {
			return false;
		}
		
		return true;

	}

	//キーワードで検索
	public List<ProductBean> searchByKeyword(String keyword) {

		List<ProductBean> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement st = null;

		try {

			con = getConnection();
			st = con.prepareStatement("select * from product where name like ? order by category_id asc , id asc");
			st.setString(1, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				ProductBean product = new ProductBean();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(Integer.toString(rs.getInt("price")));
				product.setStock(Integer.toString(rs.getInt("stock")));
				product.setDescription(rs.getString("description"));
				product.setCategoryId(rs.getString("category_id"));
				product.setFileName(rs.getString("file_name"));

				list.add(product);

			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
	
	//商品番号で商品を取得　（もしかしたら使わないかもしれない（20210616））（そんなことなかった（20210621））
	public ProductBean searchById(String id) {
		
		ProductBean product = new ProductBean();
		
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			con = getConnection();
			st = con.prepareStatement("select * from product where id=?");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(Integer.toString(rs.getInt("price")));
				product.setStock(Integer.toString(rs.getInt("stock")));
				product.setDescription(rs.getString("description"));
				product.setCategoryId(rs.getString("category_id"));
				product.setFileName(rs.getString("file_name"));
				
			} else {
				
				product = null;
				
			}
			
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	
	}

	//商品を並べ替える
	public List<ProductBean> sort(String[] order) {
		
		List<ProductBean> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement st = null;

		try {

			con = getConnection();
			st = con.prepareStatement("select * from product order by " + order[0] + " " + order[1] + ", category_id asc");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				ProductBean product = new ProductBean();
				
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(Integer.toString(rs.getInt("price")));
				product.setStock(Integer.toString(rs.getInt("stock")));
				product.setDescription(rs.getString("description"));
				product.setCategoryId(rs.getString("category_id"));
				product.setFileName(rs.getString("file_name"));

				list.add(product);

			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
	
	//商品を追加
	public boolean insert(ProductBean product) {
		
		boolean result = false;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			con = getConnection();
			st = con.prepareStatement("insert into product value(?, ?, ?, ?, ?, ?, ?)");
			
			//商品番号生成
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMddHHmmss");
			String id = "P" + format.format(date);
			
			st.setString(1, id);
			st.setString(2, product.getName());
			st.setInt(3, Integer.parseInt(product.getPrice()));
			st.setInt(4, Integer.parseInt(product.getStock()));
			st.setString(5, product.getDescription());
			st.setString(6, product.getCategoryId());
			st.setString(7, product.getFileName());
			
			int line = st.executeUpdate();
			
			if (line > 0) {
				result = true;
			}
			
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	//商品情報を変更
	public boolean update(ProductBean product) {

		boolean result = false;
		Connection con = null;
		PreparedStatement st = null;

		try {

			con = getConnection();
			st = con.prepareStatement("update product "
					+ "set name=?, price=?, stock=?, description=?, category_id=?, file_name=? "
					+ "where id=?");

			st.setString(1, product.getName());
			st.setInt(2, Integer.parseInt(product.getPrice()));
			st.setInt(3, Integer.parseInt(product.getStock()));
			st.setString(4, product.getDescription());
			st.setString(5, product.getCategoryId());
			st.setString(6, product.getFileName());
			st.setString(7, product.getId());

			int line = st.executeUpdate();

			if (line > 0) {
				result = true;
			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//商品を削除
	public boolean delete(String id) {

		boolean result = false;
		Connection con = null;
		PreparedStatement st = null;
		
		try {
			
			con = getConnection();
			st = con.prepareStatement("delete from product where id=?");
			st.setString(1, id);
			
			int line = st.executeUpdate();
			
			if (line > 0) {
				result = true;
			}
			
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}
