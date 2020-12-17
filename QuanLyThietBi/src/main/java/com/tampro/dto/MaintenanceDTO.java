package com.tampro.dto;

import java.util.Date;

import com.tampro.utils.Constant;

public class MaintenanceDTO  extends Base{
	private int id;
	private ProductDTO productDTO;
	private String description;
	private int status;
	private String name;
	private int idProduct;
	private Date dateTo;
	private Date dateFrom;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public String statusToString() {
		if(this.status == Constant._ED) {
			return "Đã hoàn thành";
		}else {
			return "Đang xử lý";
		}
	}
	public String statusToStringCp() {
		if(this.status == Constant._ED) {
			return "Da hoan thanh";
		}else {
			return "Dang xu ly";
		}
	}
	
}
