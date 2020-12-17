package com.tampro.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.MaintenanceDTO;
import com.tampro.report.ReportGoodsIssueExcel;
import com.tampro.report.ReportGoodsIssuePdf;
import com.tampro.report.ReportGoodsReceiptExcel;
import com.tampro.report.ReportGoodsReceiptPdf;
import com.tampro.report.ReportMaintenanceExcel;
import com.tampro.report.ReportMaintenancePdf;
import com.tampro.service.InvoiceService;
import com.tampro.service.MaintenanceService;
import com.tampro.utils.Constant;
import com.tampro.utils.Paging;


@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	MaintenanceService maintenanceService;
	@Autowired
	InvoiceService invoiceService;
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		 
	}
	@GetMapping(value = {"/maintenance/","/maintenance"})
	public String redirectMaintenance() {
		return "redirect:/statistics/maintenance/1";
	}
	@RequestMapping(value = {"/maintenance/{page}"})
	public String statisticsMaintenance(Model model,@PathVariable("page") int page,@ModelAttribute("searchForm") MaintenanceDTO  maintenanceDTO) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<MaintenanceDTO> list = maintenanceService.getAll(maintenanceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		return "statistics-maintenance";
	}
	@PostMapping(value = {"/maintenance/export-excel"})
	public ModelAndView exportExcelMaintenance(@ModelAttribute("searchForm") MaintenanceDTO maintenanceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		List<MaintenanceDTO> list = maintenanceService.getAll(maintenanceDTO, null);
		if(maintenanceDTO.getDateTo() != null && maintenanceDTO.getDateFrom() != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			StringBuilder dateString = new StringBuilder();
			dateString.append(format.format(maintenanceDTO.getDateTo())+"_"+format.format(maintenanceDTO.getDateFrom())+"_");			
			modelAndView.addObject("dateString", dateString.toString());
		}
		modelAndView.setView(new ReportMaintenanceExcel());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	@PostMapping(value = {"/maintenance/export-pdf"})
	public ModelAndView exportPdfMaintenance(@ModelAttribute("searchForm") MaintenanceDTO maintenanceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		List<MaintenanceDTO> list = maintenanceService.getAll(maintenanceDTO, null);
		modelAndView.setView(new ReportMaintenancePdf());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	@GetMapping(value = {"/goods-receipt/","/goods-receipt"})
	public String redirectGoodsReceipt() {
		return "redirect:/statistics/goods-receipt/1";
	}
	@RequestMapping("/goods-receipt/{page}")
	public String listGoodsReceipt(Model model,@ModelAttribute("searchForm") InvoiceDTO invoiceDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		invoiceDTO.setType(Constant.GOODS_RECEIPT);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		return "statistics-goodsreceipt";
	}
	@PostMapping(value = {"/goods-receipt/export-excel"})
	public ModelAndView exportExcelGoodsReceipt(@ModelAttribute("searchForm") InvoiceDTO invoiceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		invoiceDTO.setType(Constant.GOODS_RECEIPT);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, null);
		if(invoiceDTO.getDateTo() != null && invoiceDTO.getDateFrom() != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			StringBuilder dateString = new StringBuilder();
			dateString.append(format.format(invoiceDTO.getDateTo())+"_"+format.format(invoiceDTO.getDateFrom())+"_");			
			modelAndView.addObject("dateString", dateString.toString());
		}
		modelAndView.setView(new ReportGoodsReceiptExcel());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	@PostMapping(value = {"/goods-receipt/export-pdf"})
	public ModelAndView exportPdfGoodsReceipt(@ModelAttribute("searchForm") InvoiceDTO invoiceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		invoiceDTO.setType(Constant.GOODS_RECEIPT);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, null); 
		modelAndView.setView(new ReportGoodsReceiptPdf());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	@GetMapping(value = {"/goods-issue/","/goods-issue"})
	public String redirectGoodsIssue() {
		return "redirect:/statistics/goods-issue/1";
	}
	@RequestMapping("/goods-issue/{page}")
	public String listredirectGoodsIssue(Model model,@ModelAttribute("searchForm") InvoiceDTO invoiceDTO,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		invoiceDTO.setType(Constant.GOODS_ISSUE);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
	 
		return "statistics-goodsissue";
	}
	@PostMapping(value = {"/goods-issue/export-excel"})
	public ModelAndView exportExcelGoodIssue(@ModelAttribute("searchForm") InvoiceDTO invoiceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		invoiceDTO.setType(Constant.GOODS_ISSUE);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, null);
		if(invoiceDTO.getDateTo() != null && invoiceDTO.getDateFrom() != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			StringBuilder dateString = new StringBuilder();
			dateString.append(format.format(invoiceDTO.getDateTo())+"_"+format.format(invoiceDTO.getDateFrom())+"_");			
			modelAndView.addObject("dateString", dateString.toString());
		}
		modelAndView.setView(new ReportGoodsIssueExcel());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	@PostMapping(value = {"/goods-issue/export-pdf"})
	public ModelAndView exportPdfGoodIssue(@ModelAttribute("searchForm") InvoiceDTO invoiceDTO) {
		ModelAndView modelAndView = new ModelAndView();
		invoiceDTO.setType(Constant.GOODS_ISSUE);
		List<InvoiceDTO> list = invoiceService.getAll(invoiceDTO, null);
		 
		modelAndView.setView(new ReportGoodsIssuePdf());
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@GetMapping("/chart")
	public String chart(Model model, HttpSession session) {
		  
		initPieChart(model);
		initBarChart(model); 
		return "chart";
	}
	public void initPieChart(Model model) {
		 
		 List<Map<String,Object>> listIssued = new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> listReceipt = new ArrayList<Map<String,Object>>();
		 
		 for(Map<String,Object> item :	invoiceService.getPieChart(Constant.GOODS_RECEIPT,2020)) {	 
			 Map<String,Object> pieChartProductReceipt = new HashedMap(); 
			 pieChartProductReceipt.put("label",item.get("code") );
			 pieChartProductReceipt.put("value",item.get("count") );
			 listReceipt.add(pieChartProductReceipt );
		 }  
		 
		 
		 for(Map<String,Object> item :	invoiceService.getPieChart(Constant.GOODS_ISSUE,2020)) {	  
			 Map<String,Object> pieChartProductIssued = new HashedMap();
			 pieChartProductIssued.put("label",item.get("code") );
			 pieChartProductIssued.put("value",item.get("count") );
			 listIssued.add(pieChartProductIssued );
		 }
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonGoodsReceipt = mapper.writeValueAsString(listReceipt);
			String jsonGoodsIssued = mapper.writeValueAsString(listIssued);
			System.out.println(jsonGoodsIssued);
			System.out.println(jsonGoodsReceipt);
			model.addAttribute("pieChartJsonReceipt", jsonGoodsReceipt);
			model.addAttribute("pieChartJsonIssued", jsonGoodsIssued);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initBarChart(Model model) {
		 ObjectMapper mapper = new ObjectMapper();
		 Map<Integer,Object> mapReceipt = new  HashMap(); 	
		 Map<Integer,Object> mapIssued = new  HashMap(); 	
		 
		 TreeMap<Integer,Object>	countGoodsReceipt = new TreeMap<Integer, Object>();
		 TreeMap<Integer,Object> 	countGoodsIssued = new TreeMap<Integer, Object>();
		 
		 for(Integer i = 1 ; i <= 12 ;i++) {
			 mapReceipt.put(i, 0);
			 mapIssued.put(i, 0);
			 countGoodsReceipt.put(i, 0);		
			 countGoodsIssued.put(i, 0);
		 }
		 for(Map<String,Object> item :	invoiceService.getBarChart(Constant.GOODS_RECEIPT,2020)) {
			 mapReceipt.put(Integer.parseInt(item.get("month").toString()), item.get("price"));
		 }
		 for(Map<String,Object> item :	invoiceService.getBarChart(Constant.GOODS_ISSUE,2020)) {
			 mapIssued.put(Integer.parseInt(item.get("month").toString()), item.get("price"));	
		 }
		 List<Map<String,Object>> listReceipt = new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> listIssued = new ArrayList<Map<String,Object>>();
		 for(Integer key : mapReceipt.keySet()) {
			 Map<String,Object> receipt = new  HashMap();
			 Map<String,Object> issued = new  HashMap(); 
			 receipt.put("label", key);
			 receipt.put("value",mapReceipt.get(key));
			 listReceipt.add(receipt);
			 issued.put("label", key);
			 issued.put("value",mapIssued.get(key));
			 listIssued.add(issued);
		 }
 
		try {
			 
			String jsonGoodsReceipt = mapper.writeValueAsString(listReceipt);
			String jsonGoodsIssued = mapper.writeValueAsString(listIssued);
			System.out.println(jsonGoodsReceipt);
			System.out.println(jsonGoodsIssued);
			model.addAttribute("barcharJsonReceipt", jsonGoodsReceipt);
			model.addAttribute("barcharJsonIssued", jsonGoodsIssued);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		 
		 
		 for(Map<String,Object> item :	invoiceService.getCountBarChart(Constant.GOODS_RECEIPT,2020)) {	  
			 countGoodsReceipt.put(Integer.parseInt(item.get("month").toString()), item.get("count"));
		 }
 		  
		 for(Map<String,Object> item :	invoiceService.getCountBarChart(Constant.GOODS_ISSUE,2020)) {	  
			 countGoodsIssued.put(Integer.parseInt(item.get("month").toString()), item.get("count"));
		 }
		 List<Map<String,Object>> listCountReceipt = new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> listCountIssued = new ArrayList<Map<String,Object>>();
		 for(Integer key : countGoodsReceipt.keySet()) {
			 Map<String,Object> receipt = new  HashMap();
			 Map<String,Object> issued = new  HashMap(); 
			 receipt.put("label", key);
			 receipt.put("value",countGoodsReceipt.get(key));
			 listCountReceipt.add(receipt);
			 issued.put("label", key);
			 issued.put("value",countGoodsIssued.get(key));
			 listCountIssued.add(issued);
		 }
		try {
			 
			String jsonCountgoodsReceipt = mapper.writeValueAsString(listCountReceipt);
			String jsonCountgoodsIssued = mapper.writeValueAsString(listCountIssued);
			System.out.println(jsonCountgoodsReceipt);
			System.out.println(jsonCountgoodsIssued);
			model.addAttribute("barcharCountJsonReceipt", jsonCountgoodsReceipt);
			model.addAttribute("barcharCountJsonIssued", jsonCountgoodsIssued);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
