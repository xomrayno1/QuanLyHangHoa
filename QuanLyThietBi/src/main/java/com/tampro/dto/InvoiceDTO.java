package com.tampro.dto;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceDTO  extends Base{
	private int id;
	private int type;
	private UserDTO userDTO;
	private ProductDTO productDTO;
	private BigDecimal price;
	private Date dateTo;
	private Date dateFrom;
	private InventoryDTO inventoryDTO;
	private int quantity;
	private int invenId;
	private int productId;
	private int suppId;
	private String suppName;
	private String cusName;
	private int cusId;
	private String description;
	private BigDecimal totalPrice;
	
	
	
	
	public InvoiceDTO() {
		super();
	}
	public InvoiceDTO(int type) {
		 
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getInvenId() {
		return invenId;
	}
	public void setInvenId(int invenId) {
		this.invenId = invenId;
	}
	public InventoryDTO getInventoryDTO() {
		return inventoryDTO;
	}
	public void setInventoryDTO(InventoryDTO inventoryDTO) {
		this.inventoryDTO = inventoryDTO;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getTotalPrice() {
		this.totalPrice = this.price.multiply(new BigDecimal(this.quantity));
		return totalPrice;
	}
	@Override
	public String toString() {
		return "InvoiceDTO [id=" + id + ", type=" + type + ", userDTO=" + userDTO + ", productDTO=" + productDTO
				+ ", price=" + price + ", dateTo=" + dateTo + ", dateFrom=" + dateFrom + ", inventoryDTO="
				+ inventoryDTO + ", quantity=" + quantity + ", invenId=" + invenId + ", productId=" + productId
				+ ", suppId=" + suppId + ", suppName=" + suppName + ", cusName=" + cusName + ", cusId=" + cusId
				+ ", description=" + description + ", totalPrice=" + totalPrice + "]";
	}
	 
	
	
	
}
