package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.ProductDTO;
import com.tampro.service.ProductService;

@Component
public class ProductValidator  implements Validator{

	@Autowired
	ProductService productService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(ProductDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProductDTO productDTO = (ProductDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");		
		if(!StringUtils.isEmpty(productDTO.getCode())) {
			List<ProductDTO> produts = productService.getAllByProperty("code", productDTO.getCode());		
			if(productDTO.getId() != 0) {
				if(!produts.isEmpty()) {
					ProductDTO current = productService.findById(productDTO.getId());
					if(!productDTO.getCode().equals(current.getCode())) {
						errors.rejectValue("code", "error.exists");
					}
				}
			}else {
				if(!produts.isEmpty()) {
					errors.rejectValue("code", "error.exists");
				}
			}
		}
		if(productDTO.getId() == 0) {
			if(productDTO.getMultipartFile().isEmpty()) {
				errors.rejectValue("multipartFile", "error.required");
			}
		}
		if(productDTO.getIdCate() == 0) {
			errors.rejectValue("idCate", "error.required");
		}
	 
		if(productDTO.getBrandId() == 0) {
			errors.rejectValue("brandId", "error.required");
		}
	}
}
