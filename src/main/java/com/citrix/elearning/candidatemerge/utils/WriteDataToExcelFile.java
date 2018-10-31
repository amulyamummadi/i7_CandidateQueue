package com.citrix.elearning.candidatemerge.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.citrix.elearning.candidatemerge.pages.BasePage;

/**
 * This util class contains functionality of writing data to excel.
 *
 * @author amulya.mummadi
 *
 */
public class WriteDataToExcelFile {
	public static Logger logger = Logger.getLogger(BasePage.class.getName());

	/**
	 * Method to write data to excel.
	 *
	 * @param candidateList
	 *            the list of candidates data.
	 */
	public static void excelFile(List<CandidatePojo> candidateList) {
		int rowNum = Integer.parseInt(PropertyUtil.getProperty("rowNum"));
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("src\\test\\resources\\testdata\\output\\CandidateDetails.xlsx"));
			/**
			 * To create a blank workbook.
			 */
			final XSSFWorkbook workbook = new XSSFWorkbook();
			/**
			 * To create a blank sheet.
			 */
			final XSSFSheet sheet = workbook.createSheet("Candaidates");
			final Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("CandidateId");
			headerRow.createCell(1).setCellValue("FirstName");
			headerRow.createCell(2).setCellValue("Lastname");
			headerRow.createCell(3).setCellValue("Result");
			headerRow.createCell(4).setCellValue("failureReason");

			for (final CandidatePojo candidate : candidateList) {
				final Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(candidate.getCandidateId());
				row.createCell(1).setCellValue(candidate.getFirstName());
				row.createCell(2).setCellValue(candidate.getLastName());
				row.createCell(3).setCellValue(candidate.getResult());
				row.createCell(4).setCellValue(candidate.getFailureReason());
				workbook.write(out);
				out.close();
				logger.info("CandidateDetails.xlsx has been written successfully");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
