package com.tampro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.dto.MaintenanceDetailDTO;
import com.tampro.dto.MaintenanceDTO;
import com.tampro.service.MaintenanceDetailService;
import com.tampro.service.MaintenanceService;
import com.tampro.utils.Constant;

@Controller
@RequestMapping("/history")
public class MaintenanceDetailController {
	@Autowired
	MaintenanceDetailService historyService;
	@Autowired
	MaintenanceService maintenanceService;
	
	@RequestMapping("/list/{id}")
	public String listHistory(Model model,@PathVariable("id") int id,HttpSession session) {
		
		List<MaintenanceDetailDTO> list = historyService.getAllByProperty("maintenance.id", id);
		model.addAttribute("list", list);
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		MaintenanceDTO maintenanceDTO =	maintenanceService.findById(id);
		model.addAttribute("maintenance", maintenanceDTO);
		model.addAttribute("submitForm", new MaintenanceDetailDTO());
		return "history-list";
	}

	
	@PostMapping(value = {"/save/{id}"})
	public String saveHistory(Model model,@ModelAttribute("submitForm") MaintenanceDetailDTO historyDTO ,@PathVariable("id") int id, HttpSession session ) {
		try {
			historyService.add(historyDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
		}
		return "redirect:/history/list/"+id;
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteHistory(Model model, @PathVariable("id")int id,@RequestParam("idMain") int idMain , HttpSession session) {
		
		MaintenanceDetailDTO historyDTO = historyService.findById(id);
		try {
			historyService.delete(historyDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
			e.printStackTrace();
		}
		return "redirect:/history/list/"+idMain;
	}
}
