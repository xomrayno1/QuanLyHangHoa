package com.tampro.service;

import java.util.List;

import com.tampro.dto.CustomerDTO;
import com.tampro.utils.Paging;

public interface CustomerService {
	void add(CustomerDTO customerDTO) throws Exception;
	void delete(CustomerDTO customerDTO) ;
	void update(CustomerDTO customerDTO) throws Exception;
	List<CustomerDTO> getAll(CustomerDTO customerDTO , Paging paging);
	List<CustomerDTO> getAllByProperty(String property , Object object);
	CustomerDTO findById(int id);
	
	CustomerDTO  findByName(String name);
}
