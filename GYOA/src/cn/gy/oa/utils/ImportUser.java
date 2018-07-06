package cn.gy.oa.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class ImportUser {
	public static void main(String[] args){
		readExcelFile();
	}

	private static void readExcelFile() {
		try {
			String sourceFile = "D:\\test.xls";
			InputStream is = new FileInputStream(sourceFile);
//			String fileStr = 
			Workbook wbs = null;
//			if()
			wbs = new HSSFWorkbook(is);
			Sheet projectSheet = (Sheet) wbs.getSheetAt(0);//获取第一个sheet
			
			//获得总的sheets,得到sheet的层数
//			Sheet[] sheets = 
		} catch (Exception e) {
			
		}
	}
	public static List<String[]> getExcelValue(Sheet sheet){
//		int rownum = sheet.getLa
//		sheet.getTextRuns()
		
		
		
		return null;
	}
}
