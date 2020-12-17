package com.tampro.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.MaintenanceDTO;
import com.tampro.dto.ProductDTO;
import com.tampro.service.MaintenanceService;
import com.tampro.service.ProductService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;
import com.tampro.validator.MaintenanceValidator;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
	@Autowired
	MaintenanceService maintenanceService;
	@Autowired
	MaintenanceValidator maintenanceValidator;
	@Autowired
	ProductService productService;
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == MaintenanceDTO.class) {
			dataBinder.setValidator(maintenanceValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/maintenance/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listMaintenance(Model model,@ModelAttribute("searchForm") MaintenanceDTO maintenanceDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(10);
		paging.setIndexPage(page);
		List<MaintenanceDTO> list = maintenanceService.getAll(maintenanceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		innitSelect(model);
		return "maintenance-list";
	}
	@GetMapping(value = {"/add"})
	public String addMaintenance(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new MaintenanceDTO());
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "maintenance-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewMaintenance(Model model,@PathVariable("id") int id) {
		MaintenanceDTO maintenanceDTO = maintenanceService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", maintenanceDTO);
		model.addAttribute("viewOnly", true);
		return "maintenance-action";
	}
	@GetMapping(value = {"/edit/{id}"})
	public String editMaintenance(Model model,@PathVariable("id") int id) {
		MaintenanceDTO maintenanceDTO = maintenanceService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", maintenanceDTO);
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "maintenance-action";
	}
	@PostMapping(value = {"/save"})
	public String saveMaintenance(Model model,@ModelAttribute("submitForm") @Validated MaintenanceDTO maintenanceDTO 
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(maintenanceDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			innitSelect(model);
			model.addAttribute("submitForm", maintenanceDTO);
			return "maintenance-action";
		}
		if(maintenanceDTO.getId() != 0) {
			try {
				maintenanceService.update(maintenanceDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				maintenanceDTO.setStatus(Constant._ING);
				maintenanceService.add(maintenanceDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/maintenance/list/1";
	}

	public void innitSelect(Model model) {
		List<ProductDTO> list = productService.getAll(null, null);
		Collections.sort(list,new Comparator<ProductDTO>() {
			public int compare(ProductDTO o1, ProductDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listProduct", list);
	}
}
