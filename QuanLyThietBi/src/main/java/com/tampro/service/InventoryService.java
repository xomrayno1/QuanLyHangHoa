package com.tampro.service;

import java.util.List;

import com.tampro.dto.InventoryDTO;
import com.tampro.utils.Paging;

public interface InventoryService {
	void add(InventoryDTO inventoryDTO) throws Exception;
	void delete(InventoryDTO inventoryDTO) ;
	void update(InventoryDTO inventoryDTO) throws Exception;
	List<InventoryDTO> getAll(InventoryDTO inventoryDTO , Paging paging);
	List<InventoryDTO> getAllByProperty(String property , Object object);
	InventoryDTO findById(int id);
	
	public InventoryDTO findByName(String name);
}
