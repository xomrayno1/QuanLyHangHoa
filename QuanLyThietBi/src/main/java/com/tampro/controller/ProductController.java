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

import com.tampro.dto.BrandDTO;
import com.tampro.dto.CategoryDTO;
import com.tampro.dto.ProductDTO;
import com.tampro.service.BrandService;
import com.tampro.service.CategoryService;
import com.tampro.service.InventoryService;
import com.tampro.service.ProductService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;
import com.tampro.validator.ProductValidator;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductValidator productValidator;
	@Autowired
	CategoryService categoryService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	BrandService brandService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == ProductDTO.class) {
			dataBinder.setValidator(productValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/product/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listProduct(Model model,@ModelAttribute("searchForm") ProductDTO productDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<ProductDTO> list = productService.getAll(productDTO, paging);
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
		return "product-list";
	}
	@GetMapping(value = {"/add"})
	public String addProduct(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new ProductDTO());
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "product-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewProduct(Model model,@PathVariable("id") int id) {
		ProductDTO productDTO = productService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", productDTO);
		model.addAttribute("viewOnly", true);
		return "product-action";
	}
	@GetMapping(value = {"/edit/{id}"})
	public String editProduct(Model model,@PathVariable("id") int id) {
		ProductDTO productDTO = productService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", productDTO);
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "product-action";
	}
	@PostMapping(value = {"/save"})
	public String saveProduct(Model model,@ModelAttribute("submitForm") @Validated ProductDTO productDTO 
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(productDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			innitSelect(model);
			model.addAttribute("submitForm", productDTO);
			return "product-action";
		}
		if(productDTO.getId() != 0) {
			try {
				productService.update(productDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				productService.add(productDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/product/list/1";
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteProduct(Model model,@PathVariable("id")int id,HttpSession session) {
		ProductDTO productDTO = productService.findById(id);
		try {
			productService.delete(productDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/product/list/1";
	}
	public void innitSelect(Model model) {
		List<CategoryDTO> categoryDTOs = categoryService.getAll(null, null);
		Collections.sort(categoryDTOs,new Comparator<CategoryDTO>() {
			public int compare(CategoryDTO o1, CategoryDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listCate", categoryDTOs);
		List<BrandDTO> brandTOs = brandService.getAll(null, null);
		Collections.sort(brandTOs,new Comparator<BrandDTO>() {
			public int compare(BrandDTO o1, BrandDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listBrand", brandTOs);
	}
	
}
