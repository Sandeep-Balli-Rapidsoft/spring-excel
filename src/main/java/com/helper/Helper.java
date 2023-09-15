package com.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Product;

@Component
public class Helper {
	
	
	public static String[] HEADERS= {
			"id",
			"name",
			"price"
	};
	
	public static String SHEET_NAME = "product_data";
	
	public static ByteArrayInputStream dataToExcel(List<Product> list) throws IOException {
		
		// Create Workbook
		Workbook workbook = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			
			//Create Sheet.
			Sheet sheet = workbook.createSheet(SHEET_NAME);
			Row row = sheet.createRow(0);
			
			for(int i = 0; i < HEADERS.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(HEADERS[i]);
			}
			
			int rowIndex = 1;
			for(Product product : list) {
				Row dataRow = sheet.createRow(rowIndex);
				
				rowIndex++; 
				
				dataRow.createCell(0).setCellValue(product.getId());
				dataRow.createCell(1).setCellValue(product.getName());
				dataRow.createCell(2).setCellValue(product.getPrice());
				
			}
			
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to export data");
		} finally {
			workbook.close();
		}
		return null;
	}

	public static Boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	public static List<Product> convertExcelToProducts(InputStream is) {
		List<Product> list = new ArrayList();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);

			int rowNumber = 0;

			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells = row.iterator();

				int cid = 0;

				Product product = new Product();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {
					case 0:
						product.setId((int) cell.getNumericCellValue());
						break;
					case 1:
						product.setName(cell.getStringCellValue());
						break;
					case 2:
						product.setPrice(cell.getNumericCellValue());
						break;
					default:
						break;
					}
					cid++;

				}
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
