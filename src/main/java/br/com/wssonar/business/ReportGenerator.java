package br.com.wssonar.business;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import br.com.wssonar.model.History;
import br.com.wssonar.model.WebService;
import br.com.wssonar.util.HistoryUtil;

@Component
public class ReportGenerator {

	/**
	 * Creates the sheet file which contains the report
	 * 
	 * @param webService
	 * @param histories
	 * @return
	 */
	public byte[] createXlsReport(WebService webService, List<History> histories) {

		//Create sheet
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Relatório de quedas");

		//Create sheet styles
		HSSFFont fontBold = workbook.createFont();
		fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFCellStyle styleTitle = workbook.createCellStyle();
		styleTitle.setFont(fontBold);
		
		HSSFCellStyle styleTableHeader = createBorderedStyle(workbook);
		styleTableHeader.setFont(fontBold);
		
		HSSFCellStyle styleTableData = createBorderedStyle(workbook);
		
		//Generate the Xls data and format the Xls data in the sheet
		formatXlsHeader(webService, histories, sheet, styleTitle, styleTableHeader);
		
		Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
		generateXlsData(histories, data);
		
		formatXlsData(sheet, styleTableData, data);
		
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		
		byte[] sheetFile = null;

		ByteArrayOutputStream excelOutput = new ByteArrayOutputStream();  
		  
		try {
			workbook.write(excelOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		sheetFile = excelOutput.toByteArray();  
		  
		System.out.println("Excel written successfully..");

		return sheetFile;
	}

	/**
	 * Creates the columns and information titles
	 * 
	 * @param webService
	 * @param histories
	 * @param sheet
	 * @param styleTitle
	 * @param styleTableHeader
	 */
	private void formatXlsHeader(WebService webService, List<History> histories, HSSFSheet sheet,
			HSSFCellStyle styleTitle, HSSFCellStyle styleTableHeader) {
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(webService.getWsName());
		cell.setCellStyle(styleTitle);
		cell = row.createCell(1);
		cell.setCellValue(webService.getWsDescription());
		cell.setCellStyle(styleTitle);
		
		String tempoTotalForaDoAr = HistoryUtil.defineDowntime(histories);
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("Tempo total fora do ar");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(1);
		cell.setCellValue(tempoTotalForaDoAr);
		cell.setCellStyle(styleTableHeader);
		
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Total de testes");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(1);
		cell.setCellValue(webService.getWsCountTotal());
		cell.setCellStyle(styleTableHeader);
		
		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Total de testes positivos");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(1);
		cell.setCellValue(webService.getWsCountPositive());
		cell.setCellStyle(styleTableHeader);
		
		row = sheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue("Total de testes negativos");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(1);
		cell.setCellValue(webService.getWsCountNegative());
		cell.setCellStyle(styleTableHeader);
		
		row = sheet.createRow(7);
		
		cell = row.createCell(0);
		cell.setCellValue("Id");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(1);
		cell.setCellValue("Data de queda");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(2);
		cell.setCellValue("Hora de queda");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(3);
		cell.setCellValue("Data de volta");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(4);
		cell.setCellValue("Hora de volta");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(5);
		cell.setCellValue("Tempo total");
		cell.setCellStyle(styleTableHeader);
		cell = row.createCell(6);
		cell.setCellValue("Erro");
		cell.setCellStyle(styleTableHeader);
	}

	/**
	 * Process the data in a map to be later inserted in the sheet
	 * 
	 * @param histories
	 * @param data
	 */
	private void generateXlsData(List<History> histories,
			Map<Integer, Object[]> data) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		
		int k = 2;
		for(int i = 0; i < histories.size(); i++) {

			History history = histories.get(i);

			String dateBackOnlineFormated = "";
			String hourBackOnlineFormated = "";

			DateTime downDateTime = new DateTime(history.getHtDownDate());
			Date downDate = downDateTime.withZone(DateTimeZone.forID("America/Sao_Paulo")).toDate();
			String dateDownFormated = dateFormat.format(downDate);
			String hourDownFormated = hourFormat.format(downDate);

			if(history.getHtBackOnline() != null) {
				DateTime backOnlineDateTime = new DateTime(history.getHtBackOnline());
				Date backOnlineDate = backOnlineDateTime.withZone(DateTimeZone.forID("America/Sao_Paulo")).toDate();
				dateBackOnlineFormated = dateFormat.format(backOnlineDate);
				hourBackOnlineFormated = hourFormat.format(backOnlineDate);
			}

			data.put(++k, new Object[] {history.getHtId().toString(), dateDownFormated, hourDownFormated, dateBackOnlineFormated, hourBackOnlineFormated, history.getHtOfflineTotalTime(),history.getHtErrorResult()});
		}
	}

	/**
	 * Inserts the data in the sheet
	 * 
	 * @param sheet
	 * @param styleTableData
	 * @param data
	 */
	private void formatXlsData(HSSFSheet sheet, HSSFCellStyle styleTableData,
			Map<Integer, Object[]> data) {
		Set<Integer> keyset = data.keySet();
		int rownum = 8;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				
				if(obj instanceof String) {
					cell.setCellValue((String)obj);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer)obj);
				}
					
				cell.setCellStyle(styleTableData);
			}
		}
	}
	
	/**
	 * Style for the sheet
	 * 
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle createBorderedStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        return style;
    }

}
