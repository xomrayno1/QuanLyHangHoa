package com.tampro.service;

import java.util.List;
import java.util.Map;

import com.tampro.dto.InvoiceDTO;
import com.tampro.utils.Paging;

public interface InvoiceService {
	void add(InvoiceDTO invoiceDTO) throws Exception;
	void update(InvoiceDTO invoiceDTO) throws Exception;
	List<InvoiceDTO> getAll(InvoiceDTO invoiceDTO , Paging paging);
	List<InvoiceDTO> getAllByProperty(String property , Object object);
	InvoiceDTO findById(int id);
	
	long getCountInvoiceByNowDate(int type);
	
	public List<Map<String , Object>>  getBarChart(int type , int year);
	public List<Map<String , Object>> getCountBarChart(int type , int year);
	
	
	public List<Map<String, Object>> getPieChart(int type, int year);
}
