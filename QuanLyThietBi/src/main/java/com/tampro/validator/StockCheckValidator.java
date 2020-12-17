package com.tampro.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tampro.dto.StockCheckDTO;

@Component
public class StockCheckValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(StockCheckDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		StockCheckDTO stockCheckDTO = (StockCheckDTO) target;
		 
		if(stockCheckDTO.getProductId() == 0) {
			errors.rejectValue("productId", "error.required");
		}
		if(stockCheckDTO.getInvenId() == 0) {
			errors.rejectValue("invenId", "error.required");
		}
		
		if(stockCheckDTO.getDateCheck() == null) {
			errors.rejectValue("dateCheck", "error.required");
		}
		 
	}

}
