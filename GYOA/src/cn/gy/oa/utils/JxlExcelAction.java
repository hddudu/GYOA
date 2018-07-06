package cn.gy.oa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author dudu
 * java读取Excel：
 * 	打开工作文件：Workbook,先用java的io流创建或者读取文件
 *  打开工作表Sheet
 *  读行 读列 
 *  进行数据操作
 *
 */
public class JxlExcelAction {
	public static void main(String[] args) {
		JxlExcelAction obj = new JxlExcelAction();
		File file = new File("filePath");
		obj.readExcel(file);
	}

	private void readExcel(File file) {
		//创建输入流,读取Excel
		try {
			InputStream is = new FileInputStream(file.getAbsolutePath());
			//jxl提供的 Workbook类
			Workbook wb = Workbook.getWorkbook(is);
			//Excel的页签数量
			Integer sheets = wb.getNumberOfSheets();
			//遍历
			for(int index = 0;index < sheets;index ++){
				//每个页签创建一个Sheet对象
				Sheet sht = wb.getSheet(index);
				//遍历每一个sheet
				for(int i = 0; i < sht.getRows(); i ++){
					//返回总行数
					for(int j = 0; j < sht.getColumns(); j ++){
						//返回总列数
						String cellInfo = sht.getCell(i, j).getContents();
						//获取某一行 某一列的格子的内容
						System.out.println("i行j列的内容是:" + cellInfo);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//写入时单元格及数据的格式化
	public static void  createExcel(){
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File("test.xls"));
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Label label = new Label(0, 0, "string");
//			new Label(c, r, cont, st)
		
			sheet.addCell(label);
//			Label label2 = new Label;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
	}
	
	//设置样式
	public static WritableCellFormat getDataCellFormat(CellType type){
		WritableCellFormat wcf = null;
		try {
			if(type == CellType.NUMBER || type==CellType.NUMBER_FORMULA){
				NumberFormat nf = new NumberFormat("#.00");
				wcf  = new  WritableCellFormat(nf);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
