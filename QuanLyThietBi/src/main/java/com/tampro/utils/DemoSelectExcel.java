package com.tampro.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DemoSelectExcel {
	public static void main(String[] args) throws IOException {
		//https://stackoverflow.com/questions/51017694/apache-poi-select-values-from-drop-down-menu
		//https://stackoverflow.com/questions/53587987/how-to-create-a-dependent-drop-down-list-using-apache-poi
		//https://stackoverflow.com/questions/11113804/how-to-create-dependent-drop-downs-in-excel-sheet-generated-using-poi
	    DataValidation dataValidation = null;
	    DataValidationConstraint constraint = null;
	    DataValidationHelper validationHelper = null;

	    XSSFWorkbook wb = new XSSFWorkbook();
	    XSSFSheet sheet = (XSSFSheet) wb.createSheet("sheet");

	    validationHelper = new XSSFDataValidationHelper(sheet);
	    CellRangeAddressList addressList = new CellRangeAddressList(0, 0, 0, 0);
	    constraint = validationHelper.createExplicitListConstraint(new String[]{"YES", "NO", "MAYBE"});
	    dataValidation = validationHelper.createValidation(constraint, addressList);
	    dataValidation.setSuppressDropDownArrow(true);
	    sheet.addValidationData(dataValidation);

	    File file = new File(Constant.ABSOLUTE_PATH);
	    FileOutputStream fileOut = new FileOutputStream(file + "abs.xls");
	    wb.write(fileOut);
	    fileOut.close();
	}
}
