package com.tampro.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.MaintenanceDTO;
import com.tampro.service.MaintenanceService;

@Component
public class MaintenanceValidator implements Validator{
	@Autowired
	MaintenanceService maintenanceService;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(MaintenanceDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MaintenanceDTO maintenanceDTO = (MaintenanceDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		 
		if(maintenanceDTO.getIdProduct() == 0) {
			errors.rejectValue("idProduct", "error.required");
		}
	}
	
	
}
