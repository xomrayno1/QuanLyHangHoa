package com.tampro.report;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tampro.dto.MaintenanceDTO;

public class ReportMaintenancePdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("Content-Type: text/html; charset=UTF-8");
		String dateString = LocalDate.now().toString();
		 
		List<MaintenanceDTO> list = (List<MaintenanceDTO>)	model.get("list");
	    Font font = new Font(Font.HELVETICA, 12, Font.BOLDITALIC);

	    document.add(new Paragraph("Type :   Bao tri "));
	    document.add(new Paragraph("Type :   "+dateString));

	    PdfPTable table = new PdfPTable(5);
	    
	    table.setWidthPercentage(100.0f); 
	    table.setWidths(new float[] {2.0f,2.0f,5.0f,4.0f,3.0f });
	    
	    PdfPCell cell = new PdfPCell();
	    cell.setPhrase(new Phrase("STT", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Thoi gian", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Ten thiet bi", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Ten nguoi phu trach", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Trang thai", font));
	    table.addCell(cell);
	    // adding rows
	    Integer index = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");	 
	    for(MaintenanceDTO item : list) {
	      index++;
	      table.addCell(index.toString());
	      table.addCell(dateFormat.format(item.getCreateDate()));
	      table.addCell(item.getProductDTO().getName());
	      table.addCell(item.getName());
	      table.addCell(item.statusToStringCp());      
	    }	    
	 
	    // adding table to document
	    document.add(table);
	}

}
