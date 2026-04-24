package com.e_comm.SolaceEventPublishing;

import java.math.BigDecimal;
import java.util.List;

import com.e_comm.entity.OrderItemEntity;

public class OrderConformationEventSent {

	private String id;
	
	private BigDecimal totalPrice;
	
	private String userEmail;
	
	private String userName;
	
	private String paymentMethod;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public OrderConformationEventSent(String id, BigDecimal totalPrice, String userEmail, String userName,
			String paymentMethod) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.userEmail = userEmail;
		this.userName = userName;
		this.paymentMethod = paymentMethod;
	}

	public OrderConformationEventSent() {
		
	}
}
