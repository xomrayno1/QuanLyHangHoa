package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.CategoryDAO;
import com.tampro.dto.CategoryDTO;
import com.tampro.entity.Category;
import com.tampro.service.CategoryService;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class CategoryServiceImpl  implements CategoryService{
	@Autowired
	CategoryDAO<Category> categoryDAO;

	public void add(CategoryDTO categoryDTO) throws Exception {
		// TODO Auto-generated method stub
		Category category = new Category();
		category.setActiveFlag(1);
		category.setCreateDate(new Date());
		category.setName(categoryDTO.getName());
		category.setUpdateDate(new Date());
		category.setDescription(categoryDTO.getDescription());
		categoryDAO.insert(category);
	}

	public void delete(CategoryDTO categoryDTO)  {
		// TODO Auto-generated method stub
		Category category = new Category();
		category.setActiveFlag(0);
		category.setId(categoryDTO.getId());
		category.setCreateDate(categoryDTO.getCreateDate());
		category.setName(categoryDTO.getName());
		category.setUpdateDate(new Date());
		category.setDescription(categoryDTO.getDescription());
		categoryDAO.delete(category);
	}

	public void update(CategoryDTO categoryDTO) throws Exception {
		// TODO Auto-generated method stub
		Category category = new Category();
		category.setActiveFlag(1);
		category.setId(categoryDTO.getId());
		category.setCreateDate(categoryDTO.getCreateDate());
		category.setName(categoryDTO.getName());
		category.setUpdateDate(new Date());
		category.setDescription(categoryDTO.getDescription());
		categoryDAO.update(category);
	}

	public List<CategoryDTO> getAll(CategoryDTO categoryDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(categoryDTO != null) {
//			if(categoryDTO.getId() != 0) {
//				queryStr.append(" and model.id = :id ");
//				mapParams.put("id", categoryDTO.getId());
//			}
			if(!StringUtils.isEmpty(categoryDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+categoryDTO.getName()+"%");
			}
		}
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for(Category category : categoryDAO.findAll(mapParams, queryStr.toString(), paging)) {
			CategoryDTO dto = ConvertDTO.convertCategoryToDTO(category);
			list.add(dto);
		}
		return list;
	}

	public List<CategoryDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for(Category category : categoryDAO.findByProperty(property, object)) {
			CategoryDTO dto = ConvertDTO.convertCategoryToDTO(category);
			list.add(dto);
		}
		return list;
	}

	public CategoryDTO findById(int id) {
		// TODO Auto-generated method stub
		Category category = categoryDAO.findById(Category.class, id);
		CategoryDTO dto = ConvertDTO.convertCategoryToDTO(category);
		return dto;
	}

}
