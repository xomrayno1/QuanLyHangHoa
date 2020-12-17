package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.CategoryDTO;
import com.tampro.service.CategoryService;

@Component
public class CategoryValidator  implements Validator{
	@Autowired
	CategoryService categoryService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(CategoryDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CategoryDTO categoryDTO = (CategoryDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		if(!StringUtils.isEmpty(categoryDTO.getName())) {
			List<CategoryDTO> categories = categoryService.getAllByProperty("name", categoryDTO.getName());		
			if(categoryDTO.getId() != 0) {
				if(!categories.isEmpty()) {
					CategoryDTO current = categoryService.findById(categoryDTO.getId());
					if(!categoryDTO.getName().equals(current.getName())) {
						errors.rejectValue("name", "error.exists");
					}
				}
			}else {
				if(!categories.isEmpty()) {
					errors.rejectValue("name", "error.exists");
				}
			}
		}
	}
}
