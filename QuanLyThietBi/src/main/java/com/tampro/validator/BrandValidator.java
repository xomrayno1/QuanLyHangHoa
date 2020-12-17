package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.BrandDTO;
import com.tampro.service.BrandService;

@Component
public class BrandValidator implements Validator {
	@Autowired
	BrandService brandService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(BrandDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		BrandDTO brandDTO = (BrandDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "error.required");
		 
	 
		
		if(!StringUtils.isEmpty(brandDTO.getName())) {
			List<BrandDTO> categories = brandService.getAllByProperty("code", brandDTO.getCode());		
			if(brandDTO.getId() != 0) {
				if(!categories.isEmpty()) {
					BrandDTO current = brandService.findById(brandDTO.getId());
					if(!brandDTO.getCode().equals(current.getCode())) {
						errors.rejectValue("code", "error.exists");
					}
				}
			}else {
				if(!categories.isEmpty()) {
					errors.rejectValue("code", "error.exists");
				}
			}
		}
	}
}
