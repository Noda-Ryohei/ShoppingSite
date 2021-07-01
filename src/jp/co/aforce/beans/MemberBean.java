package jp.co.aforce.beans;

import java.io.Serializable;

public class MemberBean implements Serializable{
	
	private String id; //会員番号
	private String loginId; //ログインID
	private String name; //氏名
	private String password; //パスワード
//	private String emsg; //エラーメッセージ
//	private String commsg; //完了メッセージ
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String login_id) {
		this.loginId = login_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public String getEmsg()/* {
//		return emsg;
//	}
//	public void setEmsg(String emsg) {
//		this.emsg = emsg;
//	}
//	
//	public String getCommsg() {
//		return commsg;
//	}
//	public void setCommsg(String commsg) {
//		this.commsg = commsg;
//	}*/
	
	
}
