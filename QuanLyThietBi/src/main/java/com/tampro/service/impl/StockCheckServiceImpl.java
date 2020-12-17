package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.StockCheckDAO;
import com.tampro.dto.StockCheckDTO;
import com.tampro.entity.Inventory;
import com.tampro.entity.Product;
import com.tampro.entity.StockCheck;
import com.tampro.entity.User;
import com.tampro.service.ProductStockService;
import com.tampro.service.StockCheckService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class StockCheckServiceImpl  implements StockCheckService{
	@Autowired
	private StockCheckDAO<StockCheck> stockCheckDAO;
	@Autowired
	private ProductStockService productStockService;

	@Override
	public void add(StockCheckDTO stockCheckDTO) throws Exception {
		// TODO Auto-generated method stub
		StockCheck stockCheck = new StockCheck();
		stockCheck.setActiveFlag(1);
		stockCheck.setActualQuantity(stockCheckDTO.getActualQuantity());
		stockCheck.setCreateDate(new Date());
		stockCheck.setDateCheck(stockCheckDTO.getDateCheck());
		stockCheck.setDescription(stockCheckDTO.getDescription());
		stockCheck.setDifference(stockCheckDTO.getDifference());
		stockCheck.setInventory(new Inventory(stockCheckDTO.getInvenId()));
		stockCheck.setProduct(new Product(stockCheckDTO.getProductId()));
		stockCheck.setUpdateDate(new Date());
		stockCheck.setQuantity(stockCheckDTO.getQuantity());
		stockCheck.setUser(new User(stockCheckDTO.getUserId()));
		stockCheck.setStatus(stockCheckDTO.getStatus());
		if(stockCheck.getStatus() == Constant._ED) {
			productStockService.update(stockCheckDTO);
		}
		stockCheckDAO.insert(stockCheck);
		 
	}

	@Override
	public void delete(StockCheckDTO stockCheckDTO) {
		// TODO Auto-generated method stub
		StockCheck stockCheck = new StockCheck();
		stockCheck.setActiveFlag(0);
		stockCheck.setActualQuantity(stockCheckDTO.getActualQuantity());
		stockCheck.setCreateDate( stockCheckDTO.getCreateDate());
		stockCheck.setDateCheck(stockCheckDTO.getDateCheck());
		stockCheck.setDescription(stockCheckDTO.getDescription());
		stockCheck.setDifference(stockCheckDTO.getDifference());
		stockCheck.setId(stockCheckDTO.getId());
		stockCheck.setInventory(new Inventory(stockCheckDTO.getInvenId()));
		stockCheck.setProduct(new Product(stockCheckDTO.getProductId()));
		stockCheck.setUpdateDate(new Date());
		stockCheck.setQuantity(stockCheckDTO.getQuantity());
		stockCheck.setUser(new User(stockCheckDTO.getUserId()));
		stockCheck.setStatus(stockCheckDTO.getStatus());
		stockCheckDAO.delete(stockCheck);
		 
	}

	@Override
	public void update(StockCheckDTO stockCheckDTO) throws Exception {
		// TODO Auto-generated method stub
		StockCheck stockCheck = new StockCheck();
		stockCheck.setActiveFlag(stockCheckDTO.getActiveFlag());
		stockCheck.setActualQuantity(stockCheckDTO.getActualQuantity());
		stockCheck.setCreateDate(stockCheckDTO.getCreateDate());
		stockCheck.setDateCheck(stockCheckDTO.getDateCheck());
		stockCheck.setDescription(stockCheckDTO.getDescription());
		stockCheck.setDifference(stockCheckDTO.getDifference());
		stockCheck.setId(stockCheckDTO.getId());
		stockCheck.setInventory(new Inventory(stockCheckDTO.getInvenId()));
		stockCheck.setProduct(new Product(stockCheckDTO.getProductId()));
		stockCheck.setUpdateDate(new Date());
		stockCheck.setQuantity(stockCheckDTO.getQuantity());
		stockCheck.setUser(new User(stockCheckDTO.getUserId()));
		stockCheck.setStatus(stockCheckDTO.getStatus());
		if(stockCheck.getStatus() == Constant._ED) {
			productStockService.update(stockCheckDTO);
		}
		stockCheckDAO.update(stockCheck);
		 
	}

	@Override
	public List<StockCheckDTO> getAll(StockCheckDTO stockCheckDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(stockCheckDTO != null) {
			 if(stockCheckDTO.getId() != null   && stockCheckDTO.getId() != 0) {
				 queryStr.append(" and model.id = :id ");
				 mapParams.put("id", stockCheckDTO.getId());
			 }
			 if(stockCheckDTO.getStatus() != 0 ) {
				 queryStr.append(" and status = :status ");
				 mapParams.put("status", stockCheckDTO.getStatus());
			 }
		}
		queryStr.append(" ORDER BY (model.id) DESC");
		List<StockCheckDTO> list = new ArrayList<StockCheckDTO>();
		for(StockCheck stockCheck : stockCheckDAO.findAll(mapParams, queryStr.toString(), paging)) {
			StockCheckDTO dto = ConvertDTO.convertStockCheckToDTO(stockCheck);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<StockCheckDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<StockCheckDTO> list = new ArrayList<StockCheckDTO>();
		for(StockCheck stockCheck : stockCheckDAO.findByProperty(property, object)) {
			StockCheckDTO dto = ConvertDTO.convertStockCheckToDTO(stockCheck);
			list.add(dto);
		}
		return list;
	}

	@Override
	public StockCheckDTO findById(int id) {
		// TODO Auto-generated method stub
		StockCheck stockCheck = stockCheckDAO.findById(StockCheck.class, id);
		StockCheckDTO dto = ConvertDTO.convertStockCheckToDTO(stockCheck);
		return dto;
	}

}
