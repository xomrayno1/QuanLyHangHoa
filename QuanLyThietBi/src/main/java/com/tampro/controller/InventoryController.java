package com.tampro.controller;

import java.text.SimpleDateFormat;
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

import com.tampro.dto.InventoryDTO;
import com.tampro.service.InventoryService;
import com.tampro.service.ProductService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;
import com.tampro.validator.InventoryValidator;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	@Autowired
	InventoryValidator inventoryValidator;
	@Autowired
	ProductService productService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == InventoryDTO.class) {
			dataBinder.setValidator(inventoryValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/inventory/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listInventory(Model model,@ModelAttribute("searchForm") InventoryDTO inventoryDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(10);
		paging.setIndexPage(page);
		List<InventoryDTO> list = inventoryService.getAll(inventoryDTO, paging);
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
		return "inventory-list";
	}
	@GetMapping(value = {"/add"})
	public String addInventory(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new InventoryDTO());
		model.addAttribute("viewOnly", false);
		return "inventory-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewInventory(Model model,@PathVariable("id") int id) {
		InventoryDTO inventoryDTO = inventoryService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", inventoryDTO);
		model.addAttribute("viewOnly", true);
		return "inventory-action";
	}
	@GetMapping(value = {"/edit/{id}"})
	public String editInventory(Model model,@PathVariable("id") int id) {
		InventoryDTO inventoryDTO = inventoryService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", inventoryDTO);
		model.addAttribute("viewOnly", false);
		return "inventory-action";
	}
	@GetMapping(value = {"/stock/{id}"})
	public String stockInventory(Model model,@PathVariable("id") int id) {
		
		return "inventory-stock";
	}
	@PostMapping(value = {"/save"})
	public String saveInventory(Model model,@ModelAttribute("submitForm") @Validated InventoryDTO inventoryDTO 
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(inventoryDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			model.addAttribute("submitForm", inventoryDTO);
			return "inventory-action";
		}
		if(inventoryDTO.getId() != 0) {
			try {
				inventoryService.update(inventoryDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				inventoryService.add(inventoryDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/inventory/list/1";
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteInventory(Model model,@PathVariable("id")int id,HttpSession session) {
		InventoryDTO inventoryDTO = inventoryService.findById(id);
		try {
			inventoryService.delete(inventoryDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/inventory/list/1";
	}
}
