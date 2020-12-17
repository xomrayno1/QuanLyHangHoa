package com.tampro.service;

import java.util.List;

import com.tampro.dto.SupplierDTO;
import com.tampro.utils.Paging;

public interface SupplierService {
	void add(SupplierDTO supplierDTO) throws Exception;
	void delete(SupplierDTO supplierDTO) ;
	void update(SupplierDTO supplierDTO) throws Exception;
	List<SupplierDTO> getAll(SupplierDTO supplierDTO , Paging paging);
	List<SupplierDTO> getAllByProperty(String property , Object object);
	SupplierDTO findById(int id);
	
	public SupplierDTO findByName(String name);
}
