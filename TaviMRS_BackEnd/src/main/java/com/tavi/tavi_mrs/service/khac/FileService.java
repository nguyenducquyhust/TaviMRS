package com.tavi.tavi_mrs.service.khac;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public interface FileService {

    String writeDocx(XWPFDocument document, String fileName);

    String writeExcel(XSSFWorkbook workbook, String fileName);

    String writeHTML(String htmlString, String fileName);

    String convertHtmlToPDF(String htmlFileName, String pdfFileName);
}
