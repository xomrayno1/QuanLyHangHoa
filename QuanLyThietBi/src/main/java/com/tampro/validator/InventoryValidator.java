package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.InventoryDTO;
import com.tampro.service.InventoryService;

@Component
public class InventoryValidator  implements Validator{
	@Autowired
	InventoryService inventoryService;
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(InventoryDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		InventoryDTO inventoryDTO = (InventoryDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		if(!StringUtils.isEmpty(inventoryDTO.getName())) {
			List<InventoryDTO> inventories = inventoryService.getAllByProperty("name", inventoryDTO.getName());		
			if(inventoryDTO.getId() != 0) {
				if(!inventories.isEmpty()) {
					InventoryDTO current = inventoryService.findById(inventoryDTO.getId());
					if(!inventoryDTO.getName().equals(current.getName())) {
						errors.rejectValue("name", "error.exists");
					}
				}
			}else {
				if(!inventories.isEmpty()) {
					errors.rejectValue("name", "error.exists");
				}
			}
		}
	}

}
