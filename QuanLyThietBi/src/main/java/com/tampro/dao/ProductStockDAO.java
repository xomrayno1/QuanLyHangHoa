package com.tampro.dao;

import com.tampro.entity.ProductInStock;

public interface ProductStockDAO<E> extends BaseDAO<E> {
	ProductInStock findStockByProductIdAndInventoryId(int productId, int inventoryId);
	
	long getTotalProductInStock(); 
	
	long getCountProductInStockByProductAndInventory(int productId, int inventoryId); 
}
