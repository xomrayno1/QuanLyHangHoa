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

import com.tampro.dto.CategoryDTO;
import com.tampro.service.CategoryService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;
import com.tampro.validator.CategoryValidator;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryValidator categoryValidator;
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == CategoryDTO.class) {
			dataBinder.setValidator(categoryValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/category/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listCategory(Model model,@ModelAttribute("searchForm") CategoryDTO categoryDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<CategoryDTO> list = categoryService.getAll(categoryDTO, paging);
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
		return "category-list";
	}
	@GetMapping(value = {"/add"})
	public String addCategory(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new CategoryDTO());
		model.addAttribute("viewOnly", false);
		return "category-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewCategory(Model model,@PathVariable("id") int id) {
		CategoryDTO categoryDTO = categoryService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", categoryDTO);
		model.addAttribute("viewOnly", true);
		return "category-action";
	}
	@GetMapping(value = {"/edit/{id}"})
	public String editCategory(Model model,@PathVariable("id") int id) {
		CategoryDTO categoryDTO = categoryService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", categoryDTO);
		model.addAttribute("viewOnly", false);
		return "category-action";
	}
	@PostMapping(value = {"/save"})
	public String saveCategory(Model model,@ModelAttribute("submitForm") @Validated CategoryDTO categoryDTO 
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(categoryDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			model.addAttribute("submitForm", categoryDTO);
			return "category-action";
		}
		if(categoryDTO.getId() != 0) {
			try {
				categoryService.update(categoryDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				categoryService.add(categoryDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/category/list/1";
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteCategory(Model model,@PathVariable("id")int id,HttpSession session) {
		CategoryDTO categoryDTO = categoryService.findById(id);
		try {
			categoryService.delete(categoryDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/category/list/1";
	}
}
