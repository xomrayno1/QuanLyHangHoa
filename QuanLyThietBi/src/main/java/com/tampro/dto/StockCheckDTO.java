package com.tampro.dto;

import java.util.Date;

import com.tampro.utils.Constant;

public class StockCheckDTO extends Base{
	private Integer id;
	private	int userId;
	private String userName;
	private int productId;
	private String productName;
	private int invenId;
	private String invenName;
	private int quantity;
	private int actualQuantity;
	private int difference ;
	private String description;
	private Date dateCheck;
	
	 
	private int status;
	
	
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	 
	public int getInvenId() {
		return invenId;
	}
	public void setInvenId(int invenId) {
		this.invenId = invenId;
	}
	 
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getActualQuantity() {
		return actualQuantity;
	}
	public void setActualQuantity(int actualQuantity) {
		this.actualQuantity = actualQuantity;
	}
	public int getDifference() {
		return difference;
	}
	public void setDifference(int difference) {
		this.difference = difference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateCheck() {
		return dateCheck;
	}
	public void setDateCheck(Date dateCheck) {
		this.dateCheck = dateCheck;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInvenName() {
		return invenName;
	}
	public void setInvenName(String invenName) {
		this.invenName = invenName;
	}
	 
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 
	 
	public String getStatusUTF8() {
		if(this.status == Constant._ED) {
			return "Đã kiểm kê";
		}else {
			return "Đang kiểm kê";
		}
	}
	public String getStatusCp2() {
		if(this.status == Constant._ED) {
			return "Da kiem ke";
		}else {
			return "Dang kiem ke";
		}
	}
	
	
}
