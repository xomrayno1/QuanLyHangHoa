package com.tampro.service;

import java.util.List;

import com.tampro.dto.BrandDTO;
import com.tampro.utils.Paging;

public interface BrandService {
	void add(BrandDTO brandDTO) throws Exception;
	void delete(BrandDTO brandDTO) ;
	void update(BrandDTO brandDTO) throws Exception;
	List<BrandDTO> getAll(BrandDTO brandDTO , Paging paging);
	List<BrandDTO> getAllByProperty(String property , Object object);
	BrandDTO findById(int id);
}
