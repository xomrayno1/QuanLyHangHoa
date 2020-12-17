package com.tampro.service;

import java.util.List;

import com.tampro.dto.CategoryDTO;
import com.tampro.utils.Paging;

public interface CategoryService {
	void add(CategoryDTO categoryDTO) throws Exception;
	void delete(CategoryDTO categoryDTO) ;
	void update(CategoryDTO categoryDTO) throws Exception;
	List<CategoryDTO> getAll(CategoryDTO categoryDTO , Paging paging);
	List<CategoryDTO> getAllByProperty(String property , Object object);
	CategoryDTO findById(int id);
}
