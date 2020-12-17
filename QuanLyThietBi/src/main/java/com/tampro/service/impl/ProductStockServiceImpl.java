package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.ProductStockDAO;
import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.ProductStockDTO;
import com.tampro.dto.StockCheckDTO;
import com.tampro.entity.Inventory;
import com.tampro.entity.Product;
import com.tampro.entity.ProductInStock;
import com.tampro.service.InvoiceService;
import com.tampro.service.ProductStockService;
import com.tampro.service.StockCheckService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class ProductStockServiceImpl  implements ProductStockService{

	@Autowired
	ProductStockDAO<ProductInStock> productStockDAO;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	StockCheckService stockCheckService;
	
	public void addOrUpdate(InvoiceDTO invoiceDTO) throws Exception  {
		// TODO Auto-generated method stub
		ProductInStock productStock = productStockDAO.findStockByProductIdAndInventoryId(invoiceDTO.getProductId(), invoiceDTO.getInvenId());
		if(invoiceDTO.getId() == 0) {
			if(invoiceDTO.getType() == Constant.GOODS_RECEIPT) {			
				if(productStock == null) {
					addStock(invoiceDTO);
				}else {
					productStock.setPrice(invoiceDTO.getPrice());
					productStock.setQuantity(productStock.getQuantity() + invoiceDTO.getQuantity());
					productStockDAO.update(productStock);
				}
			}else {
				if(productStock != null) {				
					if(invoiceDTO.getQuantity()  <= productStock.getQuantity()) {
						productStock.setQuantity(productStock.getQuantity() - invoiceDTO.getQuantity());
						productStockDAO.update(productStock);
					}else {
						throw new Exception("Số lượng trong kho không đủ để xuất");
					}
				}else {
					throw new NullPointerException("Không tìm thấy đối tượng trong kho để xuất");
				}
			}
		}else {	
			InvoiceDTO currentInvoice = invoiceService.findById(invoiceDTO.getId());
			if(invoiceDTO.getProductId()  == currentInvoice.getProductId()) {
				if(invoiceDTO.getType()== Constant.GOODS_RECEIPT) {
					//-				
					if(productStock != null) {
						productStock.setPrice(invoiceDTO.getPrice());
						productStock.setQuantity(productStock.getQuantity() - currentInvoice.getQuantity() + invoiceDTO.getQuantity());
					}
				}else {
					//+
					if(productStock != null) {
						productStock.setQuantity(productStock.getQuantity() + currentInvoice.getQuantity()  -  invoiceDTO.getQuantity());
					}
				}
				productStockDAO.update(productStock);
			}else { //update lại stock cũ
					// tạo hoặc update stock mới
				ProductInStock oldStock =
						productStockDAO.findStockByProductIdAndInventoryId(currentInvoice.getProductId(), currentInvoice.getInvenId());
				if(invoiceDTO.getType()== Constant.GOODS_RECEIPT) {
					//-				
					oldStock.setQuantity(oldStock.getQuantity() - currentInvoice.getQuantity());
					oldStock.setPrice(currentInvoice.getPrice());
					productStockDAO.update(oldStock);
					if(productStock != null) {
						productStock.setPrice(invoiceDTO.getPrice());
						productStock.setQuantity(productStock.getQuantity() + invoiceDTO.getQuantity());
						productStockDAO.update(productStock);
					}else {
						addStock(invoiceDTO);
					}
				}else {
					//+
					oldStock.setQuantity(oldStock.getQuantity() + currentInvoice.getQuantity());
					oldStock.setPrice(currentInvoice.getPrice());
					productStockDAO.update(oldStock);
					if(productStock != null) {
						productStock.setQuantity(productStock.getQuantity() - invoiceDTO.getQuantity());
						productStockDAO.update(productStock);
					}else {
						 addStock(invoiceDTO);
					}
				}
				productStockDAO.update(oldStock);
			}
		}
		 
	}
	public void addStock(InvoiceDTO invoiceDTO) {
		ProductInStock	productStock = new ProductInStock();
		productStock.setActiveFlag(1);
		productStock.setCreateDate(new Date());		
		productStock.setProduct(new Product(invoiceDTO.getProductId()));
		productStock.setQuantity(invoiceDTO.getQuantity());
		productStock.setStock(new Inventory(invoiceDTO.getInvenId()));
		productStock.setPrice(invoiceDTO.getPrice());
		productStock.setUpdateDate(new Date());
		productStockDAO.insert(productStock);
	}

	


	public List<ProductStockDTO> getAll(ProductStockDTO productStockDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(productStockDTO != null) {
			if(productStockDTO.getProductId() != 0) {
				queryStr.append(" and  model.product.id = :productId ");
				mapParams.put("productId", productStockDTO.getProductId());
			}
			if(productStockDTO.getStockId() != 0) {
				queryStr.append(" and  model.stock.id = :invenId ");
				mapParams.put("invenId", productStockDTO.getStockId());
			}
		}
		List<ProductStockDTO> list = new ArrayList<ProductStockDTO>();
		for(ProductInStock productStock : productStockDAO.findAll(mapParams, queryStr.toString(), paging)) {
			ProductStockDTO dto = ConvertDTO.convertProductStockToDTO(productStock);
			list.add(dto);
		}
		return list;
	}

	public List<ProductStockDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<ProductStockDTO> list = new ArrayList<ProductStockDTO>();
		for(ProductInStock productStock : productStockDAO.findByProperty(property, object)) {
			ProductStockDTO dto = ConvertDTO.convertProductStockToDTO(productStock);
			list.add(dto);
		}
		return list;
	}

	public ProductStockDTO findById(int id) {
		// TODO Auto-generated method stub
		ProductInStock productStock = productStockDAO.findById(ProductInStock.class, id);
		ProductStockDTO dto = ConvertDTO.convertProductStockToDTO(productStock);
		return dto;
	}

	public void delete(ProductStockDTO productStockDTO) {
		// TODO Auto-generated method stub
		ProductInStock productStock = new ProductInStock();
		productStock.setId(productStockDTO.getId());
		productStock.setCreateDate(productStockDTO.getCreateDate());
		productStock.setActiveFlag(0);
		productStock.setPrice(productStockDTO.getPrice());
		productStock.setProduct(new Product(productStockDTO.getProductId()));
		productStock.setQuantity(productStockDTO.getQuantity());
		productStock.setStock(new Inventory(productStockDTO.getStockId()));
		productStock.setUpdateDate(new Date());
		productStockDAO.delete(productStock);
	}




	public long getTotalProductInStock() {
		// TODO Auto-generated method stub
		return productStockDAO.getTotalProductInStock();
	}
	@Override
	public ProductStockDTO findByProductAndInventory(int idProduct, int idInventory) {
		// TODO Auto-generated method stub
		  
		ProductInStock productStock = productStockDAO.findStockByProductIdAndInventoryId(idProduct, idInventory);
		ProductStockDTO dto = ConvertDTO.convertProductStockToDTO(productStock);
		return dto;
	}
	@Override
	public void update(StockCheckDTO stockCheckDTO) {
		// TODO Auto-generated method stub
		ProductInStock productStock = 
				productStockDAO.findStockByProductIdAndInventoryId(stockCheckDTO.getProductId(), stockCheckDTO.getInvenId());
		if(productStock != null) {	
			productStock.setQuantity(stockCheckDTO.getActualQuantity());
			productStockDAO.update(productStock);
		}else {
			if(stockCheckDTO.getId() != null && stockCheckDTO.getId() != 0) {
				StockCheckDTO current =   stockCheckService.findById(stockCheckDTO.getId());
				ProductInStock newProductStock = 
						productStockDAO.findStockByProductIdAndInventoryId(current.getProductId(), current.getInvenId());
				newProductStock.setQuantity(current.getQuantity());
				productStockDAO.update(newProductStock);
			}
				productStock = new ProductInStock();
				productStock.setQuantity(stockCheckDTO.getActualQuantity());
				productStock.setStock(new Inventory(stockCheckDTO.getInvenId()));
				productStock.setProduct(new Product(stockCheckDTO.getProductId()));
				productStock.setActiveFlag(1);
				productStock.setCreateDate(new Date());
				productStock.setUpdateDate(new Date());
				productStockDAO.insert(productStock);
		}
		 
	}
	@Override
	public long getCountProductInStockByProductAndInventory(int productId, int inventoryId) {
		// TODO Auto-generated method stub
		return productStockDAO.getCountProductInStockByProductAndInventory(productId, inventoryId);
	}
	

	







}
