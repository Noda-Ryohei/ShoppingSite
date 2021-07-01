package jp.co.aforce.beans;

import java.io.Serializable;
import java.util.Map;

public class OrderBean implements Serializable {

	private Map<ProductBean, Integer> cart;
	private String id;
	private String date;
	private String memberId;
	private String deliveryName;
	private String postalCode;
	private String deliveryAddress;
	private String paymentId;
	
	public Map<ProductBean, Integer> getCart() {
		return cart;
	}
	public void setCart(Map<ProductBean, Integer> cart) {
		this.cart = cart;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	

	
	
}
