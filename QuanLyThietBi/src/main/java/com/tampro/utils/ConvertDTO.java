package com.tampro.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tampro.dto.BrandDTO;
import com.tampro.dto.CategoryDTO;
import com.tampro.dto.CustomerDTO;
import com.tampro.dto.InventoryDTO;
import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.MaintenanceDTO;
import com.tampro.dto.MaintenanceDetailDTO;
import com.tampro.dto.ProductDTO;
import com.tampro.dto.ProductStockDTO;
import com.tampro.dto.RolesDTO;
import com.tampro.dto.StockCheckDTO;
import com.tampro.dto.SupplierDTO;
import com.tampro.dto.UserDTO;
import com.tampro.entity.Brand;
import com.tampro.entity.Category;
import com.tampro.entity.Customer;
import com.tampro.entity.Inventory;
import com.tampro.entity.Invoice;
import com.tampro.entity.Maintenance;
import com.tampro.entity.MaintenanceDetail;
import com.tampro.entity.Product;
import com.tampro.entity.ProductInStock;
import com.tampro.entity.Roles;
import com.tampro.entity.StockCheck;
import com.tampro.entity.Supplier;
import com.tampro.entity.User;

public class ConvertDTO {

	public static ProductDTO convertProductToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setActiveFlag(product.getActiveFlag());
		CategoryDTO categoryDTO = convertCategoryToDTO(product.getCategory());
		productDTO.setCategoryDTO(categoryDTO);
		productDTO.setCode(product.getCode());
		productDTO.setCreateDate(product.getCreateDate());
		productDTO.setDescription(product.getDescription());
		productDTO.setId(product.getId());
		productDTO.setImageUrl(product.getImageUrl());
		productDTO.setName(product.getName());
		productDTO.setUpdateDate(product.getUpdateDate());
		productDTO.setIdCate(product.getCategory().getId());
	 
		if(product.getBrand()  != null) {
			productDTO.setBrandId(product.getBrand().getId());
			productDTO.setBrandName(product.getBrand().getName());
		}
		 
		return productDTO;
	}
	public static CategoryDTO convertCategoryToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setActiveFlag(category.getActiveFlag());
		categoryDTO.setCreateDate(category.getCreateDate());
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setUpdateDate(category.getUpdateDate());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}
	public static MaintenanceDetailDTO convertHistoryToDTO(MaintenanceDetail history) {
		MaintenanceDetailDTO historyDTO = new MaintenanceDetailDTO();
		historyDTO.setActiveFlag(history.getActiveFlag());
		historyDTO.setCreateDate(history.getCreateDate());
		historyDTO.setId(history.getId());
		historyDTO.setDescription(history.getDescription());
		historyDTO.setImgUrl(history.getImgUrl());	
		historyDTO.setMaintenanceId(history.getMaintenance().getId());
		historyDTO.setUpdateDate(history.getUpdateDate());
		//MaintenanceDTO maintenanceDTO = convertMaintenanceToDTO(history.getMaintenance());
		//historyDTO.setMaintenanceDTO(maintenanceDTO);
		return historyDTO;
	}
	public static UserDTO convertUserToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setActiveFlag(user.getActiveFlag());
		userDTO.setCreateDate(user.getCreateDate());
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setName(user.getName());
		Set<RolesDTO> roles = new HashSet<RolesDTO>();
		user.getRoles().forEach(item ->{
			RolesDTO rolesDTO = convertRoleToDTO(item);
			roles.add(rolesDTO);
		});
		List<Integer> role = new ArrayList<Integer>();
		user.getRoles().forEach(item ->{
			role.add(item.getId());
		});
		userDTO.setRoleDTOs(roles);
		userDTO.setIntegers(role);
		userDTO.setUsername(user.getUsername());
		userDTO.setUpdateDate(user.getUpdateDate());
		return userDTO;
	}
	public static RolesDTO convertRoleToDTO(Roles roles) {
		RolesDTO rolesDTO = new RolesDTO();
		rolesDTO.setActiveFlag(roles.getActiveFlag());
		rolesDTO.setCreateDate(roles.getCreateDate());
		rolesDTO.setId(roles.getId());
		rolesDTO.setName(roles.getName());
		rolesDTO.setUpdateDate(roles.getUpdateDate());
		return rolesDTO;
	}
	
	public static InventoryDTO convertInventoryToDTO(Inventory stock) {
		InventoryDTO stockDTO = new InventoryDTO();
		stockDTO.setActiveFlag(stock.getActiveFlag());
		stockDTO.setCreateDate(stock.getCreateDate());
		stockDTO.setId(stock.getId());
		stockDTO.setName(stock.getName());
		stockDTO.setAddress(stock.getAddress());
		stockDTO.setUpdateDate(stock.getUpdateDate());
		return stockDTO;
	}
	public static ProductStockDTO convertProductStockToDTO(ProductInStock productStock) {
		ProductStockDTO productStockDTO = new ProductStockDTO();
		productStockDTO.setActiveFlag(productStock.getActiveFlag());
		productStockDTO.setCreateDate(productStock.getCreateDate());
		productStockDTO.setId(productStock.getId());
		ProductDTO productDTO = convertProductToDTO(productStock.getProduct());
		productStockDTO.setProductDTO(productDTO);
		productStockDTO.setQuantity(productStock.getQuantity());
		productStockDTO.setPrice(productStock.getPrice());
		productStockDTO.setStockId(productStock.getStock().getId());
		productStockDTO.setUpdateDate(productStock.getUpdateDate());
		InventoryDTO inventoryDTO = convertInventoryToDTO(productStock.getStock());
		productStockDTO.setInventoryDTO(inventoryDTO);
		return productStockDTO;
	}
	public static MaintenanceDTO convertMaintenanceToDTO(Maintenance maintenance) {
		MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
		maintenanceDTO.setActiveFlag(maintenance.getActiveFlag());
		maintenanceDTO.setCreateDate(maintenance.getCreateDate());
		maintenanceDTO.setId(maintenance.getId());
		ProductDTO productDTO = convertProductToDTO(maintenance.getProduct());
		maintenanceDTO.setProductDTO(productDTO);
		maintenanceDTO.setStatus(maintenance.getStatus());
		maintenanceDTO.setDescription(maintenance.getDescription());
		maintenanceDTO.setUpdateDate(maintenance.getUpdateDate());
		maintenanceDTO.setName(maintenance.getName());
		maintenanceDTO.setIdProduct(maintenance.getProduct().getId());
		return maintenanceDTO;
	}
	public static SupplierDTO convertSupplierToDTO(Supplier supplier) {
		SupplierDTO supplierDTO = new SupplierDTO();
		supplierDTO.setActiveFlag(supplier.getActiveFlag());
		supplierDTO.setAddress(supplier.getAddress());
		supplierDTO.setCode(supplier.getCode());
		supplierDTO.setCreateDate(supplier.getCreateDate());
		supplierDTO.setEmail(supplier.getEmail());
		supplierDTO.setId(supplier.getId());
		supplierDTO.setName(supplier.getName());
		supplierDTO.setPhone(supplier.getPhone());
		supplierDTO.setUpdateDate(supplier.getUpdateDate());
		return supplierDTO;
	}
	public static CustomerDTO convertCustomerToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setActiveFlag(customer.getActiveFlag());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setCreateDate(customer.getCreateDate());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setPhone(customer.getPhone());
		customerDTO.setUpdateDate(customer.getUpdateDate());
		return customerDTO;
	}
	public static BrandDTO convertBrandToDTO(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setActiveFlag(brand.getActiveFlag());
		 
		brandDTO.setCreateDate(brand.getCreateDate());
		brandDTO.setEmail(brand.getEmail());
		brandDTO.setId(brand.getId());
		brandDTO.setName(brand.getName());
		 
		brandDTO.setUpdateDate(brand.getUpdateDate());
		return brandDTO;
	}
 
	public static InvoiceDTO convertInvoiceToDTO(Invoice invoice) {
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setActiveFlag(invoice.getActiveFlag());
		invoiceDTO.setCreateDate(invoice.getCreateDate());
		invoiceDTO.setId(invoice.getId());
		invoiceDTO.setPrice(invoice.getPrice());
		ProductDTO productDTO = convertProductToDTO(invoice.getProduct());
		invoiceDTO.setProductDTO(productDTO);
		invoiceDTO.setType(invoice.getType());
		UserDTO userDTO = convertUserToDTO(invoice.getUser());
		invoiceDTO.setUserDTO(userDTO);
		invoiceDTO.setUpdateDate(invoice.getUpdateDate());
		invoiceDTO.setQuantity(invoice.getQuantity());
		invoiceDTO.setInvenId(invoice.getInventory().getId());
		InventoryDTO inventoryDTO = convertInventoryToDTO(invoice.getInventory());
		invoiceDTO.setInventoryDTO(inventoryDTO);
		invoiceDTO.setProductId(invoice.getProduct().getId());
		
		if(invoice.getCustomer() != null) {
			invoiceDTO.setCusId(invoice.getCustomer().getId());
			invoiceDTO.setCusName(invoice.getCustomer().getName());
		}
		if(invoice.getSupplier()  != null) {
			invoiceDTO.setSuppId(invoice.getSupplier().getId());
			invoiceDTO.setSuppName(invoice.getSupplier().getName());
		}
		return invoiceDTO;
	}
	public static StockCheckDTO convertStockCheckToDTO(StockCheck stockCheck) {
		StockCheckDTO checkDTO = new StockCheckDTO();
		checkDTO.setActiveFlag(stockCheck.getActiveFlag());
		checkDTO.setActualQuantity(stockCheck.getActualQuantity());
		checkDTO.setDateCheck(stockCheck.getDateCheck());
		checkDTO.setCreateDate(stockCheck.getCreateDate());
		checkDTO.setDescription(stockCheck.getDescription());
		checkDTO.setDifference(stockCheck.getDifference());
		checkDTO.setId(stockCheck.getId());
		checkDTO.setInvenId(stockCheck.getInventory().getId());
		checkDTO.setInvenName(stockCheck.getInventory().getName());
		checkDTO.setProductId(stockCheck.getProduct().getId());
		checkDTO.setProductName(stockCheck.getProduct().getName());
		checkDTO.setQuantity(stockCheck.getQuantity());
		checkDTO.setUpdateDate(stockCheck.getUpdateDate());
		checkDTO.setUserId(stockCheck.getUser().getId());
		checkDTO.setUserName(stockCheck.getUser().getName());
		checkDTO.setStatus(stockCheck.getStatus());
		return checkDTO;
	}
}
