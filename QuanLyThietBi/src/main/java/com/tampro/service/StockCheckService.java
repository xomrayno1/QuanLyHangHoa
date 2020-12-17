package com.tampro.service;

import java.util.List;

import com.tampro.dto.CategoryDTO;
import com.tampro.dto.StockCheckDTO;
import com.tampro.utils.Paging;

public interface StockCheckService {
	void add(StockCheckDTO stockCheckDTO) throws Exception;
	void delete(StockCheckDTO stockCheckDTO) ;
	void update(StockCheckDTO stockCheckDTO) throws Exception;
	List<StockCheckDTO> getAll(StockCheckDTO stockCheckDTO , Paging paging);
	List<StockCheckDTO> getAllByProperty(String property , Object object);
	StockCheckDTO findById(int id);
}
