package com.tavi.tavi_mrs.service_impl.khac;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.service.khac.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${mrs.file.tmp.download-path}")
    private String TMP_URL;

    @Value("${mrs.file.tmp.folder-path}")
    private String TMP_DIRECTORY;

    @Value("${wkhtmltopdf-bin}")
    private String WKHTMLTOPDF_COMMAND;

    @Override
    public String writeDocx(XWPFDocument document, String fileName) {
        try {
            File outFile = new File(TMP_DIRECTORY + fileName + ".docx");
            outFile.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(outFile);
            document.write(out);
            out.close();
            return TMP_URL + fileName + ".docx";
        } catch (Exception ex) {
            LOGGER.error("createDocx error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String writeExcel(XSSFWorkbook workbook, String fileName) {
        try {
            File outFile = new File(TMP_DIRECTORY + fileName + ".xlsx");
            outFile.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(outFile);
            workbook.write(out);
            out.close();
            return TMP_URL + fileName + ".xlsx";
        } catch (Exception ex) {
            LOGGER.error("createExcel error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String writeHTML(String htmlString, String fileName) {
        try {
            File newHtmlFile = new File(TMP_DIRECTORY + fileName + ".html");
            newHtmlFile.getParentFile().mkdirs();
            FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF-8");
            return TMP_URL + fileName + ".html";
        } catch (Exception ex) {
            LOGGER.error("createHtml error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String convertHtmlToPDF(String htmlFileName, String pdfFileName) {
        String input  = TMP_DIRECTORY + htmlFileName + ".html";
        String output = TMP_DIRECTORY + pdfFileName + ".pdf";
        try {
            List<String> commandList = new ArrayList<>(Arrays.asList(WKHTMLTOPDF_COMMAND, input, output));
            ProcessBuilder build = new ProcessBuilder(commandList);
            build.redirectErrorStream(true);
            Process process = build.start();
            // to read the output
            BufferedReader inputStream  = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            LOGGER.info("convertHtmlToPDF Progress");
            while ((s = inputStream.readLine()) != null) {
                System.out.println(s);
                LOGGER.info(s);
                if (s.contains("Done")) return TMP_URL + pdfFileName + ".pdf";
            }
            return null;
        } catch (Exception ex) {
            LOGGER.error("convertFromHTMLToPDF error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
