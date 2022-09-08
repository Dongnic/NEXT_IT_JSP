package com.study.excel.service;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class SimpleExcelService {
	XSSFWorkbook wb = null;
	Sheet sheet = null;
	Row row = null;
	Cell cell = null;
	List<?> list = null;
	Class<?> clazz = null;
	public SimpleExcelService(String sheetName, Class<?> clazz, List<?> list) {
		wb = new XSSFWorkbook();
		sheet = wb.createSheet(sheetName);
		this.list = list;
		this.clazz = clazz;
	}
	public void createExcel() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i);
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			// 메소드 이름은 필드 카멜낙타식 ( boNo -> getBoNo()) 이라고 가정
			int cellCount = 0;
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					if (method.getReturnType().getSimpleName().equals("String")) {
						cell = row.createCell(cellCount++);
						cell.setCellValue( (String) method.invoke(list.get(i)) );
					} else if (method.getReturnType().getName().equals("int")) {
						cell = row.createCell(cellCount++);
						cell.setCellValue( (int) method.invoke(list.get(i)) );
					}
				}
			}
//			for (Field field : fields) {
//				if (field.getType().equals("String")) {
//					cell = row.createCell(cellCount++);
//					cell.setCellValue( (String) field.invoke(list.get(i)) );
//				} else if (field.getType().equals("int")) {
//					cell = row.createCell(cellCount++);
//					cell.setCellValue( (int) field.invoke(list.get(i)) );
//				}
//			}
		}
	}
	public void outputStream(HttpServletResponse resp) throws IOException {
		// 컨텐츠 타입과 파일명 지정
		resp.setContentType("ms-vnd/excel");
		resp.setHeader("Content-Disposition", "attachment;filename=test.xlsx"); // 파일이름지정.
		// response OutputStream에 엑셀 작성
		wb.write(resp.getOutputStream());
	}
}