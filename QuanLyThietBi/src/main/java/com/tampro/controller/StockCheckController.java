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

import com.tampro.dto.InventoryDTO;
import com.tampro.dto.ProductDTO;
import com.tampro.dto.StockCheckDTO;
import com.tampro.dto.UserDTO;
import com.tampro.service.InventoryService;
import com.tampro.service.ProductService;
import com.tampro.service.StockCheckService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;
import com.tampro.validator.StockCheckValidator;

@Controller
@RequestMapping("/stock-check")
public class StockCheckController {
	@Autowired
	StockCheckService stockCheckService;
	@Autowired
	StockCheckValidator stockCheckValidator;
	@Autowired
	InventoryService invenService;
	@Autowired
	ProductService proService;
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == StockCheckDTO.class) {
			dataBinder.setValidator(stockCheckValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/stock-check/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listStockCheck(Model model,@ModelAttribute("searchForm") StockCheckDTO stockCheckDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<StockCheckDTO> list = stockCheckService.getAll(stockCheckDTO, paging);
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
		return "stock-check-list";
	}
	@GetMapping(value = {"/add"})
	public String addStockCheck(Model model) {
		innitSelect(model);
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new StockCheckDTO());
		model.addAttribute("viewOnly", false);
		return "stock-check-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewStockCheck(Model model,@PathVariable("id") int id) {
		innitSelect(model);
		StockCheckDTO stockCheckDTO = stockCheckService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", stockCheckDTO);
		model.addAttribute("viewOnly", true);
		return "stock-check-action";
	}
	@GetMapping(value = {"/edit/{id}"})
	public String editStockCheck(Model model,@PathVariable("id") int id) {
		innitSelect(model);
		StockCheckDTO stockCheckDTO = stockCheckService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", stockCheckDTO);
		model.addAttribute("viewOnly", false);
		return "stock-check-action";
	}
	@PostMapping(value = {"/save"})
	public String saveStockCheck(Model model,@ModelAttribute("submitForm") @Validated StockCheckDTO stockCheckDTO 
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			innitSelect(model);
			if(stockCheckDTO.getId() != null && stockCheckDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			model.addAttribute("submitForm", stockCheckDTO);
			return "stock-check-action";
		}
		if(stockCheckDTO.getId() != null && stockCheckDTO.getId() != 0) {
			try {
				 
				stockCheckService.update(stockCheckDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				UserDTO userDTO = (UserDTO)	session.getAttribute(Constant.USER_INFO);
				stockCheckDTO.setUserId(userDTO.getId());
				stockCheckService.add(stockCheckDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/stock-check/list/1";
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteStockCheck(Model model,@PathVariable("id")int id,HttpSession session) {
		StockCheckDTO stockCheckDTO = stockCheckService.findById(id);
		try {
			stockCheckService.delete(stockCheckDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/stock-check/list/1";
	}
	public void innitSelect(Model model) {
		List<ProductDTO> list = proService.getAll(null, null);
		Collections.sort(list,new Comparator<ProductDTO>() {
			public int compare(ProductDTO o1, ProductDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listProduct", list);
		List<InventoryDTO> listInven = invenService.getAll(null, null);
		Collections.sort(listInven,new Comparator<InventoryDTO>() {
			public int compare(InventoryDTO o1, InventoryDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listInven", listInven);
		 
	}
}
