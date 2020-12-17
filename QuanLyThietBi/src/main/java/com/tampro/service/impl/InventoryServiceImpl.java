package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.InventoryDAO;
import com.tampro.dto.InventoryDTO;
import com.tampro.entity.Inventory;
import com.tampro.service.InventoryService;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryDAO<Inventory> inventoryDAO;
	
	
	public void add(InventoryDTO inventoryDTO) throws Exception {
		// TODO Auto-generated method stub
		Inventory inventory = new Inventory();
		inventory.setActiveFlag(1);
		inventory.setAddress(inventoryDTO.getAddress());
		inventory.setCreateDate(new Date());
		inventory.setName(inventoryDTO.getName());
		inventory.setUpdateDate(new Date());
		inventoryDAO.insert(inventory);
	}

	public void delete(InventoryDTO inventoryDTO) {
		// TODO Auto-generated method stub
		Inventory inventory = new Inventory();
		inventory.setActiveFlag(0);
		inventory.setAddress(inventoryDTO.getAddress());
		inventory.setCreateDate(inventoryDTO.getCreateDate());
		inventory.setId(inventoryDTO.getId());
		inventory.setName(inventoryDTO.getName());
		inventory.setUpdateDate(new Date());
		inventoryDAO.delete(inventory);
	}

	public void update(InventoryDTO inventoryDTO) throws Exception {
		// TODO Auto-generated method stub
		Inventory inventory = new Inventory();
		inventory.setActiveFlag(inventoryDTO.getActiveFlag());
		inventory.setAddress(inventoryDTO.getAddress());
		inventory.setCreateDate(inventoryDTO.getCreateDate());
		inventory.setId(inventoryDTO.getId());
		inventory.setName(inventoryDTO.getName());
		inventory.setUpdateDate(new Date());
		inventoryDAO.update(inventory);
	}

	public List<InventoryDTO> getAll(InventoryDTO inventoryDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(inventoryDTO != null) {
 
			if(!StringUtils.isEmpty(inventoryDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+inventoryDTO.getName()+"%");
			}
		}
		List<InventoryDTO> list = new ArrayList<InventoryDTO>();
		for(Inventory inventory : inventoryDAO.findAll(mapParams, queryStr.toString(), paging)) {
			InventoryDTO dto = ConvertDTO.convertInventoryToDTO(inventory);
			list.add(dto);
		}
		return list;
	}

	public List<InventoryDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<InventoryDTO> list = new ArrayList<InventoryDTO>();
		for(Inventory inventory : inventoryDAO.findByProperty(property, object)) {
			InventoryDTO dto = ConvertDTO.convertInventoryToDTO(inventory);
			list.add(dto);
		}
		return list;
	}

	public InventoryDTO findById(int id) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryDAO.findById(Inventory.class, id);
		InventoryDTO dto = ConvertDTO.convertInventoryToDTO(inventory);
		return dto;
	}

	@Override
	public InventoryDTO findByName(String name) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryDAO.findByName(name);
		InventoryDTO dto = ConvertDTO.convertInventoryToDTO(inventory);
		return dto;
	}

}
