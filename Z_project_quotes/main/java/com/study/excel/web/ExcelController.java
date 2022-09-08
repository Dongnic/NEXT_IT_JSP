package com.study.excel.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.excel.service.SimpleExcelService;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;


@Controller
public class ExcelController {
	
	@Inject
	IFreeBoardService freeBoardService;
	
	@ResponseBody
	@RequestMapping("/free/excelDown")
	public void freeExcelDown(HttpServletResponse response, @ModelAttribute("searchVO") FreeBoardSearchVO searchVO) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(searchVO);
		
		// 아무 파라미터 없으니까 기본 10개의 데이터가 생깁니다.
		SimpleExcelService excelService = new SimpleExcelService("free엑셀", FreeBoardVO.class, freeBoardList);
		excelService.createExcel();
		excelService.outputStream(response);
//		// for문 반복되면서 row 반복, 안에서는 Cell을 freeBoardVO 속성 갯수만큼
//		for(int i = 0; i < freeBoardList.size(); i++) {
//			row = sheet.createRow(i);
//			FreeBoardVO freeBoard = freeBoardList.get(i);
//			int cellCount = 0;
//			cell = row.createCell(cellCount++);
//			cell.setCellValue(freeBoard.getBoNo());
//			cell = row.createCell(cellCount++);
//			cell.setCellValue(freeBoard.getBoCategory());
//			cell = row.createCell(cellCount++);
//			cell.setCellValue(freeBoard.getBoTitle());
//			cell = row.createCell(cellCount++);
//			cell.setCellValue(freeBoard.getBoWriter());
//			cell = row.createCell(cellCount++);
//			cell.setCellValue(freeBoard.getBoRegDate());
//			cell = row.createCell(cellCount++);
//			cell.setCellValue(freeBoard.getBoHit());
//		}
//		
//		// 컨텐츠 타입과 파일명 지정
//		response.setContentType("ms-vnd/excel");
//		response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");  //파일이름지정.
//		//response OutputStream에 엑셀 작성
//		wb.write(response.getOutputStream());
	
	
	}
}