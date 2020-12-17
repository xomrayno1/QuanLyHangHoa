package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.BrandDAO;
import com.tampro.dto.BrandDTO;
import com.tampro.entity.Brand;
import com.tampro.service.BrandService;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	BrandDAO<Brand> brandDAO;
	
	@Override
	public void add(BrandDTO brandDTO) throws Exception {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		brand.setActiveFlag(1);
		brand.setCreateDate(new Date());
		brand.setUpdateDate(new Date());
		brand.setName(brandDTO.getName());
		brand.setCode(brandDTO.getCode());
		brand.setEmail(brandDTO.getEmail());

		brandDAO.insert(brand);
	}

	@Override
	public void delete(BrandDTO brandDTO) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		brand.setActiveFlag(0);
		brand.setCreateDate(brandDTO.getCreateDate());
		brand.setUpdateDate(new Date());
		brand.setName(brandDTO.getName());
		brand.setCode(brandDTO.getCode());
		brand.setEmail(brandDTO.getEmail());
		brand.setId(brandDTO.getId());
		brandDAO.delete(brand);
	}

	@Override
	public void update(BrandDTO brandDTO) throws Exception {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		brand.setActiveFlag(brandDTO.getActiveFlag());
		brand.setCreateDate(brandDTO.getCreateDate());
		brand.setUpdateDate(new Date());
		
		brand.setName(brandDTO.getName());
		brand.setCode(brandDTO.getCode());
		brand.setEmail(brandDTO.getEmail());
		brand.setId(brandDTO.getId());
		brandDAO.update(brand);
	}

	@Override
	public List<BrandDTO> getAll(BrandDTO brandDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(brandDTO != null) {
 
			if(!StringUtils.isEmpty(brandDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+brandDTO.getName()+"%");
			}
		} 
		List<BrandDTO> list = new ArrayList<BrandDTO>();
		for(Brand brand : brandDAO.findAll(mapParams, queryStr.toString(), paging)) {
			BrandDTO dto = ConvertDTO.convertBrandToDTO(brand);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<BrandDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<BrandDTO> list = new ArrayList<BrandDTO>();
		for(Brand brand : brandDAO.findByProperty(property, object)) {
			BrandDTO dto = ConvertDTO.convertBrandToDTO(brand);
			list.add(dto);
		}
		return list;
	}

	@Override
	public BrandDTO findById(int id) {
		// TODO Auto-generated method stub
		Brand brand = brandDAO.findById(Brand.class, id);
		BrandDTO dto = ConvertDTO.convertBrandToDTO(brand);
		return dto;
	}

}
