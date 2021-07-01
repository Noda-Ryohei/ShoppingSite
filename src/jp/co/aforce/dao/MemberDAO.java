package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jp.co.aforce.beans.MemberBean;

public class MemberDAO extends DAO {

	//会員登録時、入力値チェック
	public boolean inputCheck(MemberBean member) {
		
		if (member.getLoginId().isEmpty() || member.getName().isEmpty() || member.getPassword().isEmpty()) {
			return false;
		}
		
		return true;
		
	}
	
	//ログイン時、入力値チェック
	public boolean loginCheck(MemberBean member) {
		
		if (member.getLoginId().isEmpty() || member.getPassword().isEmpty() ) {
			return false;
		}
		
		return true;
		
	}

	//ログイン判定、ログインID・パスワードで検索
	public MemberBean search(String loginId, String password) {

		MemberBean member = new MemberBean();

		Connection con = null;
		PreparedStatement st = null;

		try {

			con = getConnection();
			st = con.prepareStatement("select * from member where login_id = ? and password = ?");
			st.setString(1, loginId);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				member.setId(rs.getString("id"));
				member.setLoginId(rs.getString("login_id"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));

			} else {

				member = null;

			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;

	}

	//新規会員登録
	public boolean insert(MemberBean member) {

		boolean result = false;
		Connection con = null;
		PreparedStatement st = null;

		try {
			
			con = getConnection();
			st = con.prepareStatement("insert into member values(?, ?, ?, ?)");

			//会員番号生成
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMddHHmmss");
			String id = "M" + format.format(date);
			
			st.setString(1, id);
			st.setString(2, member.getLoginId());
			st.setString(3, member.getName());
			st.setString(4, member.getPassword());
			
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
