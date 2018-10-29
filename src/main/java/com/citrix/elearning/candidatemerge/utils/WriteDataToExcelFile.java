package com.citrix.elearning.candidatemerge.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This util class contains functionality of writing data from candidatePojoList
 * to excel file
 *
 * @author amulya.mummadi
 *
 */
public class WriteDataToExcelFile {

	public static void excelFile(List<CandidatePojo> candidateList) {

		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("\\src\\test\\resources\\testdata.output\\CandidateDetails.xlsx"));
			/**
			 * To create a blank workbook
			 */
			final XSSFWorkbook workbook = new XSSFWorkbook();
			/**
			 * To create a blank sheet
			 */
			final XSSFSheet sheet = workbook.createSheet("Candaidates");
			final Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("CandidateId");
			headerRow.createCell(1).setCellValue("FirstName");
			headerRow.createCell(2).setCellValue("Lastname");
			headerRow.createCell(3).setCellValue("Result");
			headerRow.createCell(4).setCellValue("failureResult");

			int rowNum = 1;
			for (final CandidatePojo candidate : candidateList) {
				final Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(candidate.getCandidateId());
				row.createCell(1).setCellValue(candidate.getFirstName());
				row.createCell(2).setCellValue(candidate.getLastName());
				row.createCell(3).setCellValue(candidate.getResult());
				row.createCell(4).setCellValue(candidate.getFailureResult());
				workbook.write(out);
				out.close();
				System.out.println("CandidateDetails.xlsx has been written successfully");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
