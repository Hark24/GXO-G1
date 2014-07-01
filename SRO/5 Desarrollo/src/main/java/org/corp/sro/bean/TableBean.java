package org.corp.sro.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

@ManagedBean(name="TableBean")
@ViewScoped
public class TableBean {

	public void postProcessXLS(Object document) {  
	    HSSFWorkbook wb = (HSSFWorkbook) document;  
	    HSSFSheet sheet = wb.getSheetAt(0);  
	    HSSFRow header = sheet.getRow(0);  
	      
	    HSSFCellStyle cellStyle = wb.createCellStyle();    
	    cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);  
	    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	      
	    for(int i=0; i < header.getPhysicalNumberOfCells();i++) {  
	        HSSFCell cell = header.getCell(i);  
	          
	        cell.setCellStyle(cellStyle);  
	    }  
	}  
}
