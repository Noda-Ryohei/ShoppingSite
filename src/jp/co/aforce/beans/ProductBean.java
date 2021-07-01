package jp.co.aforce.beans;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private String id; //商品番号
	private String name; //商品名
	private String price; //値段
	private String stock; //在庫数
	private String description; //商品説明
	private String categoryId; //カテゴリー番号
	private String fileName; //画像ファイル名
	
//	private String emsg; //エラーメッセージ
//	private String commsg; //完了メッセージ
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
//	public String getEmsg() {
//		return emsg;
//	}
//	public void setEmsg(String emsg) {
//		this.emsg = emsg;
//	}
//	
//	public String getCommsg() {
//		return commsg;
//	}
//	public void setCommsg(String commag) {
//		this.commsg = commag;
//	}
	
}
