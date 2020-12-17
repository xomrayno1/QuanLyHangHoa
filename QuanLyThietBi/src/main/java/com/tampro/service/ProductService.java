package com.tampro.service;

import java.util.List;

import com.tampro.dto.ProductDTO;
import com.tampro.utils.Paging;

public interface ProductService {
	void add(ProductDTO productDTO) throws Exception;
	void delete(ProductDTO productDTO) ;
	void update(ProductDTO productDTO) throws Exception;
	List<ProductDTO> getAll(ProductDTO productDTO , Paging paging);
	List<ProductDTO> getAllByProperty(String property , Object object);
	ProductDTO findById(int id);
	
	public ProductDTO findByName(String name);
}
