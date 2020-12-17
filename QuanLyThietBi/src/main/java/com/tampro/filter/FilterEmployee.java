package com.tampro.filter;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tampro.dto.RolesDTO;
import com.tampro.dto.UserDTO;
import com.tampro.service.UserService;
import com.tampro.utils.Constant;

public class FilterEmployee implements HandlerInterceptor{
	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session  =request.getSession();
		UserDTO userDTO = (UserDTO) session.getAttribute(Constant.USER_INFO);
		Set<RolesDTO> roles = userDTO.getRoleDTOs();
 
		if(roles != null) {
			for (RolesDTO rolesDTO : roles) {
				if(rolesDTO.getName().contains("EMPLOYEE") ) {
					return true;
				} 
			}
			response.sendRedirect(request.getContextPath() + "/access-denied");
			return false;
		}else {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
