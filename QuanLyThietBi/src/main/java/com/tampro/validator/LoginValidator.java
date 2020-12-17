package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.UserDTO;
import com.tampro.service.UserService;



@Component
public class LoginValidator  implements Validator{
	@Autowired
	UserService userService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(UserDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.required");
		UserDTO userDTO = (UserDTO) target;
		if(!StringUtils.isEmpty(userDTO.getUsername()) && !StringUtils.isEmpty(userDTO.getPassword())) {
			List<UserDTO> currentUsers = userService.getAllByProperty("username", userDTO.getUsername());
			if(!currentUsers.isEmpty()) {
				UserDTO currentUser = currentUsers.get(0);	
				if(! userDTO.getPassword().equals(currentUser.getPassword())) {
					errors.rejectValue("password", "error.login");
				}
			}else {
				errors.rejectValue("password", "error.login");
			}
		}
	}

}
