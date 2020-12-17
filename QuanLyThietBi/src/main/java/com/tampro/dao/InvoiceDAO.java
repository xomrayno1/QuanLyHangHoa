package com.tampro.dao;

import java.util.List;
import java.util.Map;

public interface InvoiceDAO<E> extends BaseDAO<E> {
	
	long getCountInvoiceByNowDate(int type);
	
	public List<Map<String , Object>> getBarChart(int type , int year);
	
	public List<Map<String , Object>> getCountBarChart(int type , int year);
	
	public List<Map<String , Object>> getPieChart(int type , int year);
}
