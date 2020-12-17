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

import com.tampro.dto.BrandDTO;
import com.tampro.service.BrandService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;
import com.tampro.validator.BrandValidator;

@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	BrandService brandService;
	@Autowired
	BrandValidator brandValidator;
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == BrandDTO.class) {
			dataBinder.setValidator(brandValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/brand/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listBrand(Model model,@ModelAttribute("searchForm") BrandDTO brandDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<BrandDTO> list = brandService.getAll(brandDTO, paging);
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
		return "brand-list";
	}
	@GetMapping(value = {"/add"})
	public String addBrand(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new BrandDTO());
		model.addAttribute("viewOnly", false);
		return "brand-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewBrand(Model model,@PathVariable("id") int id) {
		BrandDTO brandDTO = brandService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", brandDTO);
		model.addAttribute("viewOnly", true);
		return "brand-action";
	}
	@GetMapping(value = {"/edit/{id}"})
	public String editBrand(Model model,@PathVariable("id") int id) {
		BrandDTO brandDTO = brandService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", brandDTO);
		model.addAttribute("viewOnly", false);
		return "brand-action";
	}
	@PostMapping(value = {"/save"})
	public String saveBrand(Model model,@ModelAttribute("submitForm") @Validated BrandDTO brandDTO 
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(brandDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			model.addAttribute("submitForm", brandDTO);
			return "brand-action";
		}
		if(brandDTO.getId() != 0) {
			try {
				brandService.update(brandDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				brandService.add(brandDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/brand/list/1";
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteBrand(Model model,@PathVariable("id")int id,HttpSession session) {
		BrandDTO brandDTO = brandService.findById(id);
		try {
			brandService.delete(brandDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/brand/list/1";
	}
}
