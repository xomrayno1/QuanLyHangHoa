package com.tampro.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tampro.dao.ProductDAO;
import com.tampro.dto.ProductDTO;
import com.tampro.entity.Brand;
import com.tampro.entity.Category;
import com.tampro.entity.Product;
import com.tampro.service.ProductService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO<Product> productDAO;
	@Autowired
	ServletContext context;

	public void add(ProductDTO productDTO) throws Exception {
		// TODO Auto-generated method stub
		Product product = new Product();
		product.setActiveFlag(1);
		product.setCategory(new Category(productDTO.getIdCate()));
		product.setCode(productDTO.getCode());
		product.setCreateDate(new Date());
		product.setDescription(productDTO.getDescription());
		if(!productDTO.getMultipartFile().isEmpty()) {
			String images = System.currentTimeMillis()+"_"+productDTO.getMultipartFile().getOriginalFilename();
			uploadFile(productDTO.getMultipartFile(),images);
			product.setImageUrl("/upload/"+images);
			
		}
		product.setBrand(new Brand(productDTO.getBrandId()));
		product.setName(productDTO.getName());
		product.setUpdateDate(new Date());
		productDAO.insert(product);
	}

	public void delete(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product = new Product();
		product.setActiveFlag(0);
		product.setCategory(new Category(productDTO.getIdCate()));
		product.setCode(productDTO.getCode());
		product.setCreateDate(new Date());
		product.setDescription(productDTO.getDescription());
		product.setId(productDTO.getId());
		product.setImageUrl(productDTO.getImageUrl());
		product.setName(productDTO.getName());
		product.setUpdateDate(new Date());
		product.setBrand(new Brand(productDTO.getBrandId()));
		productDAO.delete(product);
	}

	public void update(ProductDTO productDTO) throws Exception {
		// TODO Auto-generated method stub
		Product product = new Product();
		product.setActiveFlag(productDTO.getActiveFlag());
		product.setCategory(new Category(productDTO.getIdCate()));
		product.setCode(productDTO.getCode());
		product.setCreateDate(productDTO.getCreateDate());
		product.setDescription(productDTO.getDescription());
		product.setId(productDTO.getId());
		if(!productDTO.getMultipartFile().isEmpty()) {
			String images = System.currentTimeMillis()+"_"+productDTO.getMultipartFile().getOriginalFilename();
			uploadFile(productDTO.getMultipartFile(),images);
			product.setImageUrl("/upload/"+images);
			
		}else {			
			product.setImageUrl(productDTO.getImageUrl());
		}
		product.setBrand(new Brand(productDTO.getBrandId()));
		product.setName(productDTO.getName());
		product.setUpdateDate(new Date());
		productDAO.update(product);
	}

	public List<ProductDTO> getAll(ProductDTO productDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(productDTO != null) {
			if(!StringUtils.isEmpty(productDTO.getCode())) {
				queryStr.append(" and model.code = :code ");
				mapParams.put("code", productDTO.getCode());
			}
			if(!StringUtils.isEmpty(productDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+productDTO.getName()+"%");
			}
			if(productDTO.getIdCate() != 0) {
				queryStr.append(" and model.category.id  = :cateId ");
				mapParams.put("cateId", productDTO.getIdCate());
			}
			if(productDTO.getBrandId() != 0) {
				queryStr.append(" and model.brand.id  = :brandId ");
				mapParams.put("brandId", productDTO.getBrandId());
			}
		}
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		for(Product product : productDAO.findAll(mapParams, queryStr.toString(), paging)) {
			ProductDTO dto = ConvertDTO.convertProductToDTO(product);
			list.add(dto);
		}
		return list;
	}

	public List<ProductDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		for(Product product : productDAO.findByProperty(property, object)) {
			ProductDTO dto = ConvertDTO.convertProductToDTO(product);
			list.add(dto);
		}
		return list;
	}

	public ProductDTO findById(int id) {
		// TODO Auto-generated method stub
		Product product = productDAO.findById(Product.class, id);
		ProductDTO dto = ConvertDTO.convertProductToDTO(product);
		return dto;
	}
	public void uploadFile(MultipartFile multipartFile,String images ) throws IOException {
		File file = new File(Constant.ABSOLUTE_PATH + images);
		String realPath = context.getRealPath("/")+"upload\\"+images;
		byte[] bytes = multipartFile.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
		stream.write(bytes);
		stream.close();
		multipartFile.transferTo(new File(realPath));
		
	}

	@Override
	public ProductDTO findByName(String name) {
		// TODO Auto-generated method stub
		Product product = productDAO.findByName(name);
		ProductDTO dto = ConvertDTO.convertProductToDTO(product);
		return dto;
	}
	

	
}
