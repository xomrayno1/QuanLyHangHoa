package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.InvoiceDAO;
import com.tampro.dto.InvoiceDTO;
import com.tampro.entity.Customer;
import com.tampro.entity.Inventory;
import com.tampro.entity.Invoice;
import com.tampro.entity.Product;
import com.tampro.entity.Supplier;
import com.tampro.entity.User;
import com.tampro.service.InvoiceService;
import com.tampro.service.ProductStockService;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InvoiceDAO<Invoice> invoiceDAO;
	@Autowired
	ProductStockService productStockService;
	
	public void add(InvoiceDTO invoiceDTO) throws Exception {
		// TODO Auto-generated method stub
		Invoice invoice = new Invoice();
		invoice.setActiveFlag(1);
		invoice.setCreateDate(new Date());
		invoice.setPrice(invoiceDTO.getPrice());
		invoice.setProduct(new Product(invoiceDTO.getProductId()));
		invoice.setType(invoiceDTO.getType());
		invoice.setUpdateDate(new Date());
		invoice.setUser(new User(invoiceDTO.getUserDTO().getId()));
		invoice.setInventory(new Inventory(invoiceDTO.getInvenId()));
		invoice.setQuantity(invoiceDTO.getQuantity());
		invoice.setDescription(invoiceDTO.getDescription());
		if(invoiceDTO.getCusId() != 0) {
			invoice.setCustomer(new Customer(invoiceDTO.getCusId()));
		}
		if(invoiceDTO.getSuppId() != 0) {
			invoice.setSupplier(new Supplier(invoiceDTO.getSuppId()));
		}
		invoice.setDescription(invoiceDTO.getDescription());
		productStockService.addOrUpdate(invoiceDTO);
		invoiceDAO.insert(invoice);
	}

	public List<InvoiceDTO> getAll(InvoiceDTO invoiceDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(invoiceDTO != null) {
			if(invoiceDTO.getInvenId() != 0) {
				queryStr.append(" and model.inventory.id = :invenId ");
				mapParams.put("invenId", invoiceDTO.getInvenId());
			}
			if(invoiceDTO.getProductId() != 0) {
				queryStr.append(" and model.product.id = :productId ");
				mapParams.put("productId", invoiceDTO.getProductId());
			}
			if(invoiceDTO.getType() != 0) {
				queryStr.append(" and model.type = :type ");
				mapParams.put("type", invoiceDTO.getType());
			}
			if(invoiceDTO.getDateTo() != null && invoiceDTO.getDateFrom() != null) {
				queryStr.append(" and date(model.createDate ) between :dateTo and :dateFrom ");
				mapParams.put("dateTo", invoiceDTO.getDateTo());
				mapParams.put("dateFrom", invoiceDTO.getDateFrom());
			}
		}
		queryStr.append(" ORDER BY (model.id) DESC");
		List<InvoiceDTO> list = new ArrayList<InvoiceDTO>();
		for(Invoice invoice : invoiceDAO.findAll(mapParams, queryStr.toString(), paging)) {
			InvoiceDTO dto = ConvertDTO.convertInvoiceToDTO(invoice);
			list.add(dto);
		}
		return list;
	}

	public List<InvoiceDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<InvoiceDTO> list = new ArrayList<InvoiceDTO>();
		for(Invoice invoice : invoiceDAO.findByProperty(property, object)) {
			InvoiceDTO dto = ConvertDTO.convertInvoiceToDTO(invoice);
			list.add(dto);
		}
		return list;
	}

	public InvoiceDTO findById(int id) {
		// TODO Auto-generated method stub
		Invoice invoice = invoiceDAO.findById(Invoice.class, id);
		InvoiceDTO dto = ConvertDTO.convertInvoiceToDTO(invoice);
		return dto;
	}

	public void update(InvoiceDTO invoiceDTO) throws Exception {
		// TODO Auto-generated method stub
		Invoice invoice = new Invoice();
		invoice.setActiveFlag(invoiceDTO.getActiveFlag());
		invoice.setCreateDate(invoiceDTO.getCreateDate());
		invoice.setId(invoiceDTO.getId());
		invoice.setPrice(invoiceDTO.getPrice());
		invoice.setProduct(new Product(invoiceDTO.getProductId()));
		invoice.setType(invoiceDTO.getType());
		invoice.setUpdateDate(new Date());
		invoice.setUser(new User(invoiceDTO.getUserDTO().getId()));
		invoice.setInventory(new Inventory(invoiceDTO.getInvenId()));
		invoice.setQuantity(invoiceDTO.getQuantity());
		invoice.setDescription(invoiceDTO.getDescription());
		if(invoiceDTO.getCusId() != 0) {
			invoice.setCustomer(new Customer(invoiceDTO.getCusId()));
		}
		if(invoiceDTO.getSuppId() != 0) {
			invoice.setSupplier(new Supplier(invoiceDTO.getSuppId()));
		}
		invoice.setDescription(invoiceDTO.getDescription());
		productStockService.addOrUpdate(invoiceDTO);
		invoiceDAO.update(invoice);
	}

	public long getCountInvoiceByNowDate(int type) {
		// TODO Auto-generated method stub
		return invoiceDAO.getCountInvoiceByNowDate(type);
	}

	@Override
	public List<Map<String , Object>>  getBarChart(int type , int year) {
		// TODO Auto-generated method stub
		return invoiceDAO.getBarChart(type,year);
	}

	@Override
	public List<Map<String, Object>> getCountBarChart(int type, int year) {
		// TODO Auto-generated method stub
		return invoiceDAO.getCountBarChart(type, year);
	}

	@Override
	public List<Map<String, Object>> getPieChart(int type, int year) {
		// TODO Auto-generated method stub
		return invoiceDAO.getPieChart(type, year);
	}

}
