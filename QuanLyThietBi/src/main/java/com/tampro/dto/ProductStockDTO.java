package com.tampro.dto;

import java.math.BigDecimal;

public class ProductStockDTO extends Base{
	private int id;
	private ProductDTO productDTO;
	private InventoryDTO inventoryDTO;
	private int stockId;
	private int quantity;
	private BigDecimal price;
	private int productId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public InventoryDTO getInventoryDTO() {
		return inventoryDTO;
	}
	public void setInventoryDTO(InventoryDTO inventoryDTO) {
		this.inventoryDTO = inventoryDTO;
	}
	
	
	
}
