package com.tampro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tampro.dto.UserDTO;
import com.tampro.service.InvoiceService;
import com.tampro.service.MaintenanceService;
import com.tampro.service.ProductStockService;
import com.tampro.service.UserService;
import com.tampro.utils.Constant;
import com.tampro.validator.LoginValidator;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired
	LoginValidator loginValidator;
	 
	@Autowired
	MaintenanceService mainteService;
	@Autowired
	ProductStockService productStockService;
	@Autowired
	InvoiceService invoiceService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		if(dataBinder.getTarget().getClass() == UserDTO.class) {
			dataBinder.setValidator(loginValidator);
		}
	}

	@GetMapping(value = {"/","/index","/home"})
	public String index(ModelMap map) {
		map.addAttribute("totalGoodsReceipt", invoiceService.getCountInvoiceByNowDate(Constant.GOODS_RECEIPT));
		map.addAttribute("totalGoodIssued",invoiceService.getCountInvoiceByNowDate(Constant.GOODS_ISSUE));
		map.addAttribute("totalUsers", userService.getTotalUser());
		map.addAttribute("totalMaintenanceIng", mainteService.getCountMaintenaceByNowDate(Constant._ING));
		map.addAttribute("totalMaintenanceEd", mainteService.getCountMaintenaceByNowDate(Constant._ED));
		map.addAttribute("totalProductInStock", productStockService.getTotalProductInStock());
		return "index";
	}
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new UserDTO());
		return "login";
	}
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		return "access-denied";
	}
	@PostMapping("/processLogin")
	public String processLogin(Model model,@ModelAttribute("loginForm") @Validated UserDTO userDTO, BindingResult result,HttpSession 
			session) {
		if(result.hasErrors()) {
			return "login";
		}
		UserDTO userInfo = userService.getAllByProperty("username", userDTO.getUsername()).get(0);
		session.setAttribute(Constant.USER_INFO,userInfo);
		return "redirect:/index";
	}
	@GetMapping("/logout")
	public String login(HttpSession session) {
		session.removeAttribute(Constant.USER_INFO);
		return "redirect:/index";
	}
}
