package com.tampro.service;

import java.util.List;

import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.ProductStockDTO;
import com.tampro.dto.StockCheckDTO;
import com.tampro.utils.Paging;

public interface ProductStockService {
	void addOrUpdate(InvoiceDTO invoiceDTO) throws Exception;
	void delete( ProductStockDTO productStockDTO ) ;
	ProductStockDTO findByProductAndInventory(int idProduct , int idInventory);
	List<ProductStockDTO> getAll(ProductStockDTO productStockDTO , Paging paging);
	List<ProductStockDTO> getAllByProperty(String property , Object object);
	ProductStockDTO findById(int id);
	long getTotalProductInStock(); 
	
	public void update(StockCheckDTO stockCheckDTO);
	
	public long getCountProductInStockByProductAndInventory(int productId, int inventoryId);
}
