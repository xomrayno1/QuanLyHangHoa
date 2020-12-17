package com.tampro.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.tampro.dto.InventoryDTO;
import com.tampro.dto.ProductDTO;
import com.tampro.dto.SupplierDTO;

public class DocumentGoodsReceipt extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder fileName = new StringBuilder("document_Nhap-Hang_.xls");
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName.toString()+"\"");
		Font font = workbook.createFont();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setBold(true);
		font.setFontHeightInPoints((short)10);
		Sheet sheet = workbook.createSheet("Nhập hàng");
		sheet.setColumnWidth(0, 15000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 7000);
		sheet.setColumnWidth(4, 7000); 
		Row row = sheet.createRow(0); 
		row.createCell(0).setCellValue("Tên thiết bị");
		row.getCell(0).setCellStyle(cellStyle);
		row.createCell(1).setCellValue("Giá");
		row.getCell(1).setCellStyle(cellStyle);
		row.createCell(2).setCellValue("Số lượng");
		row.getCell(2).setCellStyle(cellStyle);
		row.createCell(3).setCellValue("Kho");
		row.getCell(3).setCellStyle(cellStyle);
		row.createCell(4).setCellValue("Nhà cung cấp");
		row.getCell(4).setCellStyle(cellStyle);
		
		List<InventoryDTO> listInven = (List<InventoryDTO>) model.get("inventory") ;
		int size = listInven.size();
		String[] arrayInven = new String[size];
		for(int i = 0 ; i < size ;i++  ) {
			arrayInven[i] = listInven.get(i).getName();
		}
		
		List<SupplierDTO> listSup = (List<SupplierDTO>) model.get("suppliers") ;
		int supSize = listSup.size();
		String[] arraySup = new String[supSize];
		for(int i = 0 ; i < supSize ;i++  ) {
			arraySup[i] = listSup.get(i).getName();
		}	
		
		List<ProductDTO> listPro = (List<ProductDTO>) model.get("products") ;
		int proSize = listPro.size();
		String[] arrayPro = new String[proSize];
		for(int i = 0 ; i < proSize ;i++  ) {
			ProductDTO productDTO = listPro.get(i);
			arrayPro[i] = productDTO.getName();
		}	
		
		 
		HSSFDataValidation dataValidation  = dropDown( workbook,1, 0, arrayPro);
		sheet.addValidationData(dataValidation);
		
		
		 dataValidation  = dropDown( workbook,1 , 3, arrayInven);
		sheet.addValidationData(dataValidation);
		
		dataValidation  = dropDown( workbook,1 , 4, arraySup);
		sheet.addValidationData(dataValidation);
		 

	}
	private static HSSFDataValidation dropDown(Workbook workbook,int row,int column,String arr[]) {
		 
		 String dropDownName =   "DropDownValuesForColumn" + column; 
		 Sheet hidden =  workbook.createSheet(dropDownName);
	     Cell cell = null;
	     int length =  arr.length ;
	     for (int i = 0   ; i <  length ; i++)
	        {
	            String name =  arr[i];
	            Row rowIt = hidden.createRow(i);
	            cell = rowIt.createCell(0);
	            cell.setCellValue(name);
	        } 
		
	     Name namedCell = workbook.createName();
	     
	     namedCell.setNameName(dropDownName);
	     namedCell.setRefersToFormula(dropDownName+"!$A$1:$A$" + length);
	  // Sheet sheet = workbook.createSheet();
	    DVConstraint constraint = DVConstraint.createFormulaListConstraint(dropDownName);
	    CellRangeAddressList addressList = new CellRangeAddressList(row, row, column,column);
	    HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
	    int hiddenSheetIndex = workbook.getSheetIndex(hidden);
	    workbook.setSheetHidden(hiddenSheetIndex, true);
	   //sheet.addValidationData(validation); 
	     return  validation;
	}
}
