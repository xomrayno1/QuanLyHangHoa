package com.tampro.report;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.tampro.dto.MaintenanceDTO;

public class ReportMaintenanceExcel extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder fileName = new StringBuilder("report_Bao-Tri_");
		String dateString = (String)  model.get("dateString");
		if(dateString != null) {
			fileName.append(dateString);
		}
		fileName.append(".xls");
		List<MaintenanceDTO> list = (List<MaintenanceDTO>)	model.get("list");
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName.toString()+"\"");
		Font font = workbook.createFont();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setBold(true);
		 
		font.setFontHeightInPoints((short)10);
		Sheet sheet = workbook.createSheet();
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 20000);
		sheet.setColumnWidth(3, 7000);
		sheet.setColumnWidth(4, 10000);
		sheet.setColumnWidth(5, 10000);
		Row row = sheet.createRow(0);
		 
		row.createCell(0).setCellValue("#");
		row.getCell(0).setCellStyle(cellStyle);
		row.createCell(1).setCellValue("Thời gian");
		row.getCell(1).setCellStyle(cellStyle);
		row.createCell(2).setCellValue("Tên thiết bị");
		row.getCell(2).setCellStyle(cellStyle);
		row.createCell(3).setCellValue("Tên người phụ trách");
		row.getCell(3).setCellStyle(cellStyle);
		row.createCell(4).setCellValue("Trạng thái");
		row.getCell(4).setCellStyle(cellStyle);

		CellStyle cellStyleContent = workbook.createCellStyle();
		cellStyleContent.setAlignment(HorizontalAlignment.CENTER);
		if(!list.isEmpty()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			int rowIndex = 1 ;	
			for(MaintenanceDTO maintenanceDTO : list) {
				Row	rowContent = sheet.createRow(rowIndex++);			
				rowContent.createCell(0).setCellValue(rowIndex - 1);
				rowContent.getCell(0).setCellStyle(cellStyleContent);
				rowContent.createCell(1).setCellValue(dateFormat.format(maintenanceDTO.getCreateDate()));
				rowContent.getCell(1).setCellStyle(cellStyleContent);			
				rowContent.createCell(2).setCellValue(maintenanceDTO.getProductDTO().getName());
				rowContent.getCell(2).setCellStyle(cellStyleContent);	
				rowContent.createCell(3).setCellValue(maintenanceDTO.getName());
				rowContent.getCell(3).setCellStyle(cellStyleContent);
				rowContent.createCell(4).setCellValue(maintenanceDTO.statusToString());
				rowContent.getCell(4).setCellStyle(cellStyleContent);
			}
		 
		}
	}

}
