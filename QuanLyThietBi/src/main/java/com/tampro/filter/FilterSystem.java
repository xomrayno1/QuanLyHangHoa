package com.tampro.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tampro.dto.UserDTO;
import com.tampro.utils.Constant;

public class FilterSystem  implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
				 
		HttpSession session = request.getSession();
		UserDTO userDTO = (UserDTO) session.getAttribute(Constant.USER_INFO);
		if(userDTO != null) {
			return true;
		}else {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
