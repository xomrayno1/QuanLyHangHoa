package com.tampro.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.InventoryDTO;
import com.tampro.dto.ProductDTO;
import com.tampro.dto.ProductStockDTO;
import com.tampro.service.InventoryService;
import com.tampro.service.ProductService;
import com.tampro.service.ProductStockService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;

@Controller
@RequestMapping("/product-stock")
public class ProductStockController {
	@Autowired
	ProductStockService productStockService;
	@Autowired
	ProductService productService;
	@Autowired
	InventoryService inventoryService;
	

	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/product-stock/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listInvoice(Model model,@ModelAttribute("searchForm") ProductStockDTO productStockDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		
		List<ProductStockDTO> list = productStockService.getAll(productStockDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			System.out.println(session.getAttribute(Constant.MSG_ERROR));
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		innitSelect(model);
		return "product-stock-list";
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
		List<InventoryDTO> listInven = inventoryService.getAll(null, null);
		Collections.sort(listInven,new Comparator<InventoryDTO>() {
			public int compare(InventoryDTO o1, InventoryDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}		
		});
		model.addAttribute("listInven", listInven);
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteProduct(Model model,@PathVariable("id")int id,HttpSession session) {
		ProductStockDTO productStockDTO = productStockService.findById(id);
		try {
			productStockService.delete(productStockDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/product-stock/list/1";
	}
}
