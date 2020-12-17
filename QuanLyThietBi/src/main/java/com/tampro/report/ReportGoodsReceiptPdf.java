package com.tampro.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tampro.dto.InvoiceDTO;

public class ReportGoodsReceiptPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String dateString = LocalDate.now().toString();
		 
		List<InvoiceDTO> invoices = (List<InvoiceDTO>)	model.get("list");
	    Font font = new Font(Font.HELVETICA, 12, Font.BOLDITALIC);
	    
	    document.add(new Paragraph("Type :   Nhap Hang"));
	    
	    document.add(new Paragraph("Date : "+dateString));
	    
	    PdfPTable table = new PdfPTable(7);
	    table.setWidthPercentage(100.0f); 
	    table.setWidths(new float[] {2.0f,3.0f,4.0f,4.0f,3.0f, 3.0f, 3.0f});
	    
	    PdfPCell cell = new PdfPCell();
	    cell.setPhrase(new Phrase("STT", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Date", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Name Product", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Price", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Quantity", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Name User", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Inventory", font));
	    table.addCell(cell);
	    // adding rows
	    Integer index = 0;
	    int totalQuantity = 0;
		BigDecimal totalPrice = new BigDecimal(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 DecimalFormat df = new DecimalFormat("#,###.00");
	    for(InvoiceDTO invoice : invoices) {
	      index++;
	      table.addCell(index.toString());
	      table.addCell(dateFormat.format(invoice.getCreateDate()));
	      table.addCell(invoice.getProductDTO().getName());
	      table.addCell(df.format(invoice.getPrice()));
	      table.addCell(String.valueOf(invoice.getQuantity()));
	      table.addCell(invoice.getUserDTO().getName());
	      table.addCell(invoice.getInventoryDTO().getName());
	      totalPrice = totalPrice.add(invoice.getPrice());
	      totalQuantity += invoice.getQuantity();
	    }	    
	    table.addCell("Total : ");
	    table.addCell("");
	    table.addCell("");
	    table.addCell(df.format(totalPrice));
	    table.addCell(String.valueOf(totalQuantity));
	    table.addCell("");
	    table.addCell("");
	    table.addCell("");
	    // adding table to document
	    document.add(table);
	}

}
