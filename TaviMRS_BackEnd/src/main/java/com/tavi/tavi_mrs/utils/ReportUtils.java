package com.tavi.tavi_mrs.utils;

import com.tavi.tavi_mrs.entities.common.ThamQuyenCap;
import com.tavi.tavi_mrs.entities.common.TrangThaiXuLy;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeDoanhNghiepKhaiThac;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTheoLuongXeVaoMo;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTruLuongKhaiThac;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.NhatKyXeVaoMo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportUtils {

    private static String reportTimeRange(Date ngayDau, Date ngayCuoi, boolean isFormattedAsDate) {
        if (isFormattedAsDate) {
            StringBuilder reportInfo = new StringBuilder("Từ ");
            reportInfo.append(ngayDau == null || DateTimeUtils.toDateString(ngayDau).equals("01/01/1970") ? "trước" : DateTimeUtils.toDateString(ngayCuoi));
            reportInfo.append(" đến ");
            reportInfo.append(ngayCuoi == null || DateTimeUtils.toDateString(ngayCuoi).equals("31/12/9999") ? "nay" : DateTimeUtils.toDateString(ngayCuoi));
            return reportInfo.toString();
        } else {
            StringBuilder reportInfo = new StringBuilder("Từ ");
            reportInfo.append(ngayDau == null ? "trước" : DateTimeUtils.toDateTimeString(ngayCuoi));
            reportInfo.append(" đến ");
            reportInfo.append(ngayCuoi == null ? "nay" : DateTimeUtils.toDateTimeString(ngayCuoi));
            return reportInfo.toString();
        }
    }

    private static XSSFWorkbook initWorkBook(String reportName, String[] columnNames, Date ngayDau, Date ngayCuoi, boolean isFormmatedAsDate) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("0");
        XSSFCellStyle style = workbook.createCellStyle();
        // create title
        {
            style.setAlignment(HorizontalAlignment.CENTER);
            // report name
            Row row0 = sheet.createRow(0);
            Cell cellReportName = row0.createCell(0, CellType.STRING);
            cellReportName.setCellValue(reportName);
            cellReportName.setCellStyle(style);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNames.length - 1));
            // report time range
            Row row1 = sheet.createRow(1);
            Cell cellTimeRange = row1.createCell(0, CellType.STRING);
            cellTimeRange.setCellValue(reportTimeRange(ngayDau, ngayCuoi, isFormmatedAsDate));
            cellTimeRange.setCellStyle(style);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columnNames.length - 1));
        }
        // create header row
        {
            Row row = sheet.createRow(2);
            for (int i = 0; i < columnNames.length; i++) {
                Cell cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(style);
            }
        }
        return workbook;
    }

    private static XWPFDocument initDocument(String reportName, Date ngayDau, Date ngayCuoi, boolean isFormmatedAsDate) {
        XWPFDocument document = new XWPFDocument();
        document.createStyles();

        document.createNumbering();
        XWPFParagraph headingPara = document.createParagraph();
        headingPara.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun headingRun = headingPara.createRun();
        headingRun.setText(reportName);
        headingRun.addBreak();
        headingRun.setText(reportTimeRange(ngayDau, ngayCuoi, isFormmatedAsDate));
        headingRun.addBreak();

        return document;
    }

    private static String initHtml(String reportName, String[] columnNames, Date ngayDau, Date ngayCuoi, boolean isFormmatedAsDate) throws IOException {
        // get template to String
        File htmlTemplateFile = new ClassPathResource("/report/template.html").getFile();
        String htmlString = FileUtils.readFileToString(htmlTemplateFile, "UTF-8");
        // set basic information
        htmlString = StringUtils.replace(htmlString, "$reportName", reportName);
        htmlString = StringUtils.replace(htmlString, "$reportInfo", reportTimeRange(ngayDau, ngayCuoi, isFormmatedAsDate));
        // set header row
        StringBuilder headerRow = new StringBuilder("");
        for (int i = 0; i < columnNames.length; i++) headerRow.append("<th>" + columnNames[i] + "</th>");
        htmlString = StringUtils.replace(htmlString, "$headerRow", headerRow.toString());

        return htmlString;
    }

    public static XSSFWorkbook createExcelThongKeTruLuongKhaiThac(List<ThongKeTruLuongKhaiThac> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Tên doanh nghiệp", "Loại khoáng sản", "Tổng trữ lượng", "Công suất khai thác", "Trữ lượng khai thác thời gian trên", "Trữ lượng còn lại hiện tại"};
        final String reportName = "BÁO CÁO TỔNG HỢP TRỮ LƯỢNG KHAI THÁC";
        XSSFWorkbook workbook = initWorkBook(reportName, columnNames, ngayDau, ngayCuoi, true);
        XSSFSheet sheet = workbook.getSheet("0");
        // write data
        {
            int rowNum = 3;

            for (ThongKeTruLuongKhaiThac t : resultList) {
                //Row row = row
                Row row = sheet.createRow(rowNum++);
                //STT
                Cell cellSTT = row.createCell(0, CellType.STRING);
                cellSTT.setCellValue(rowNum - 3);
                //Ten Doanh nghiep
                Cell cellTenDoanhNghiep = row.createCell(1, CellType.STRING);
                cellTenDoanhNghiep.setCellValue(t.getMo().getDoanhNghiep().getTenDoanhNghiep());
                //Loai Khoang san
                Cell cellLoaiKhoangSan = row.createCell(2, CellType.STRING);
                cellLoaiKhoangSan.setCellValue(t.getMo().getKhoangSan().getTenKhoangSan());
                //Tong tru luong
                Cell cellTongTruLuong = row.createCell(3, CellType.NUMERIC);
                cellTongTruLuong.setCellValue(t.getMo().getTruLuongMo());
                //Cong suat khai thac
                Cell cellCongSuatKhaiThac = row.createCell(4, CellType.NUMERIC);
                cellCongSuatKhaiThac.setCellValue(t.getMo().getCongSuatKhaiThac());
                //Tru luong khai thac
                Cell cellTruLuongKhaiThac = row.createCell(5, CellType.NUMERIC);
                cellTruLuongKhaiThac.setCellValue(t.getTong());
                //Tru luong con lai
                Cell cellTruLuongConLai = row.createCell(6, CellType.NUMERIC);
                cellTruLuongConLai.setCellValue(t.getMo().getTruLuongConLai());

            }
        }
        for (int i = 0; i < columnNames.length; i++) sheet.autoSizeColumn(i);
        return workbook;
    }

    public static XWPFDocument createDocxThongKeTruLuongKhaiThac(List<ThongKeTruLuongKhaiThac> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Tên doanh nghiệp", "Loại khoáng sản", "Tổng trữ lượng", "Công suất khai thác", "Trữ lượng khai thác thời gian trên", "Trữ lượng còn lại hiện tại"};
        final String reportName = "BÁO CÁO TỔNG HỢP TRỮ LƯỢNG KHAI THÁC";
        XWPFDocument document = initDocument(reportName, ngayDau, ngayCuoi, true);

        XWPFTable table = document.createTable(1, columnNames.length);
        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < columnNames.length; i++) headerRow.getCell(i).setText(columnNames[i]);
        int rowNum = 1;
        for (ThongKeTruLuongKhaiThac t : resultList) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(String.valueOf(rowNum++));
            dataRow.getCell(1).setText(t.getMo().getDoanhNghiep().getTenDoanhNghiep());
            dataRow.getCell(2).setText(t.getMo().getKhoangSan().getTenKhoangSan());
            dataRow.getCell(3).setText(String.valueOf(t.getMo().getTruLuongMo()));
            dataRow.getCell(4).setText(String.valueOf(t.getMo().getCongSuatKhaiThac()));
            dataRow.getCell(5).setText(String.valueOf(t.getTong()));
            dataRow.getCell(6).setText(String.valueOf(t.getMo().getTruLuongConLai()));
        }
        return document;
    }

    public static String createHtmlThongKeTruLuongKhaiThac(List<ThongKeTruLuongKhaiThac> resultList, Date ngayDau, Date ngayCuoi) throws IOException {
        final String[] columnNames = {"STT", "Tên doanh nghiệp", "Loại khoáng sản", "Tổng trữ lượng", "Công suất khai thác", "Trữ lượng khai thác thời gian trên", "Trữ lượng còn lại hiện tại"};
        final String reportName = "BÁO CÁO TỔNG HỢP TRỮ LƯỢNG KHAI THÁC";
        String htmlString = initHtml(reportName, columnNames, ngayDau, ngayCuoi, true);
        StringBuilder dataRows = new StringBuilder("");

        int rowNum = 1;
        for (ThongKeTruLuongKhaiThac t : resultList) {
            dataRows.append("<tr>");
            dataRows.append("<td>" + rowNum++ + "</td>");
            dataRows.append("<td>" + t.getMo().getDoanhNghiep().getTenDoanhNghiep() + "</td>");
            dataRows.append("<td>" + t.getMo().getKhoangSan().getTenKhoangSan() + "</td>");
            dataRows.append("<td>" + t.getMo().getTruLuongMo() + "</td>");
            dataRows.append("<td>" + t.getMo().getCongSuatKhaiThac() + "</td>");
            dataRows.append("<td>" + t.getTong() + "</td>");
            dataRows.append("<td>" + t.getMo().getTruLuongConLai() + "</td>");
            dataRows.append("</tr>");
        }
        htmlString = StringUtils.replace(htmlString, "$dataRows", dataRows.toString());
        return htmlString;
    }

    public static XSSFWorkbook createExcelThongKeTheoLuongXeVaoMo(List<ThongKeTheoLuongXeVaoMo> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Biển số xe", "Mỏ", "Số lượt", "Tổng"};
        XSSFWorkbook workbook = initWorkBook("BÁO CÁO THEO TỔNG LƯỢNG XE RA VÀO MỎ", columnNames, ngayDau, ngayCuoi, true);
        XSSFSheet sheet = workbook.getSheet("0");
        // write data
        {
            int rowNum = 3;

            for (ThongKeTheoLuongXeVaoMo t : resultList) {
                //Row row = row
                Row row = sheet.createRow(rowNum++);
                //STT
                Cell cellSTT = row.createCell(0, CellType.STRING);
                cellSTT.setCellValue(rowNum - 3);
                //Bien so xe
                Cell cellBienSoXe = row.createCell(1, CellType.STRING);
                cellBienSoXe.setCellValue(t.getXeVanChuyen().getBienSoXe());
                //Mo
                Cell cellLoaiKhoangSan = row.createCell(2, CellType.STRING);
                cellLoaiKhoangSan.setCellValue(t.getMo().getDiaChi());
                //Số luot
                Cell cellSoLuot = row.createCell(3, CellType.NUMERIC);
                cellSoLuot.setCellValue(t.getSoLuot());
                //Tong khoi luong khai thac
                Cell cellTongKhoiLuongKhaiThac = row.createCell(4, CellType.NUMERIC);
                cellTongKhoiLuongKhaiThac.setCellValue(t.getTong());

            }
        }
        for (int i = 0; i < columnNames.length; i++) sheet.autoSizeColumn(i);
        return workbook;
    }

    public static XWPFDocument createDocxThongKeTheoLuongXeVaoMo(List<ThongKeTheoLuongXeVaoMo> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Biển số xe", "Mỏ", "Số lượt", "Tổng"};
        final String reportName = "BÁO CÁO THEO TỔNG LƯỢNG XE RA VÀO MỎ";

        XWPFDocument document = initDocument(reportName, ngayDau, ngayCuoi, true);
        XWPFTable table = document.createTable(1, columnNames.length);

        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < columnNames.length; i++) headerRow.getCell(i).setText(columnNames[i]);

        int rowNum = 1;
        for (ThongKeTheoLuongXeVaoMo t : resultList) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(String.valueOf(rowNum++));
            dataRow.getCell(1).setText(t.getXeVanChuyen().getBienSoXe());
            dataRow.getCell(2).setText(t.getMo().getDiaChi());
            dataRow.getCell(3).setText(String.valueOf(t.getSoLuot()));
            dataRow.getCell(4).setText(String.valueOf(t.getTong()));
        }
        return document;
    }

    public static String createHtmlThongKeTheoLuongXeVaoMo(List<ThongKeTheoLuongXeVaoMo> resultList, Date ngayDau, Date ngayCuoi) throws IOException {
        final String[] columnNames = {"STT", "Biển số xe", "Mỏ", "Số lượt", "Tổng"};
        final String reportName = "BÁO CÁO THEO TỔNG LƯỢNG XE RA VÀO MỎ";
        String htmlString = initHtml(reportName, columnNames, ngayDau, ngayCuoi, true);
        StringBuilder dataRows = new StringBuilder("");

        int rowNum = 1;
        for (ThongKeTheoLuongXeVaoMo t : resultList) {
            dataRows.append("<tr>");
            dataRows.append("<td>" + rowNum++ + "</td>");
            dataRows.append("<td>" + t.getXeVanChuyen().getBienSoXe() + "</td>");
            dataRows.append("<td>" + t.getMo().getDiaChi() + "</td>");
            dataRows.append("<td>" + t.getSoLuot()+ "</td>");
            dataRows.append("<td>" + t.getTong() + "</td>");
            dataRows.append("</tr>");
        }
        htmlString = StringUtils.replace(htmlString, "$dataRows", dataRows.toString());
        return htmlString;
    }

    public static XSSFWorkbook createExcelThongKeViPham(List<ViPham> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Mô tả vi phạm", "Doanh nghiệp", "Thời gian", "Trạng thái xử lý"};
        final String reportName = "BÁO CÁO THEO TỔNG LƯỢNG XE RA VÀO MỎ";
        XSSFWorkbook workbook = initWorkBook(reportName, columnNames, ngayDau, ngayCuoi, false);
        XSSFSheet sheet = workbook.getSheet("0");
        // write data
        {
            int rowNum = 3;

            for (ViPham t : resultList) {
                //Row row = row
                Row row = sheet.createRow(rowNum++);
                //STT
                Cell cellSTT = row.createCell(0, CellType.STRING);
                cellSTT.setCellValue(rowNum - 3);
                //Mo ta vi pham
                Cell cellBienSoXe = row.createCell(1, CellType.STRING);
                cellBienSoXe.setCellValue(t.getLoaiViPham().getMoTa());
                //Doanh nghiệp
                Cell cellDoanhNghiep = row.createCell(2, CellType.STRING);
                cellDoanhNghiep.setCellValue(t.getDoanhNghiep().getTenDoanhNghiep());
                //Thoi gian
                Cell cellThoiGian = row.createCell(3, CellType.STRING);
                cellThoiGian.setCellValue(DateTimeUtils.toDateTimeString(t.getThoiGianXayRa()));
                //Trang thai xu ly
                Cell cellTrangThaiXuLy = row.createCell(4, CellType.STRING);
                cellTrangThaiXuLy.setCellValue(TrangThaiXuLy.getMoTaTrangThaiXuLy(t.getTrangThaiXuLy()));

            }
        }
        for (int i = 0; i < columnNames.length; i++) sheet.autoSizeColumn(i);
        return workbook;
    }

    public static XWPFDocument createDocxThongKeViPham(List<ViPham> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Mô tả vi phạm", "Doanh nghiệp", "Thời gian", "Trạng thái xử lý"};
        final String reportName = "BÁO CÁO SAI PHẠM";

        XWPFDocument document = initDocument(reportName, ngayDau, ngayCuoi, false);
        XWPFTable table = document.createTable(1, columnNames.length);

        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < columnNames.length; i++) headerRow.getCell(i).setText(columnNames[i]);

        int rowNum = 1;
        for (ViPham t : resultList) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(String.valueOf(rowNum++));
            dataRow.getCell(1).setText(t.getLoaiViPham().getMoTa());
            dataRow.getCell(2).setText(t.getDoanhNghiep().getTenDoanhNghiep());
            dataRow.getCell(3).setText(DateTimeUtils.toDateTimeString(t.getThoiGianXayRa()));
            dataRow.getCell(4).setText(TrangThaiXuLy.getMoTaTrangThaiXuLy(t.getTrangThaiXuLy()));
        }
        return document;
    }

    public static String createHtmlThongKeViPham(List<ViPham> resultList, Date ngayDau, Date ngayCuoi) throws IOException {
        final String[] columnNames = {"STT", "Mô tả vi phạm", "Doanh nghiệp", "Thời gian", "Trạng thái xử lý"};
        final String reportName = "BÁO CÁO SAI PHẠM";
        String htmlString = initHtml(reportName, columnNames, ngayDau, ngayCuoi, false);
        StringBuilder dataRows = new StringBuilder("");

        int rowNum = 1;
        for (ViPham t : resultList) {
            dataRows.append("<tr>");
            dataRows.append("<td>" + rowNum++ + "</td>");
            dataRows.append("<td>" + t.getLoaiViPham().getMoTa() + "</td>");
            dataRows.append("<td>" + t.getDoanhNghiep().getTenDoanhNghiep() + "</td>");
            dataRows.append("<td>" + DateTimeUtils.toDateTimeString(t.getThoiGianXayRa()) + "</td>");
            dataRows.append("<td>" + TrangThaiXuLy.getMoTaTrangThaiXuLy(t.getTrangThaiXuLy()) + "</td>");
            dataRows.append("</tr>");
        }
        htmlString = StringUtils.replace(htmlString, "$dataRows", dataRows.toString());
        return htmlString;
    }

    public static XSSFWorkbook createExcelThongKeDoanhNghiepKhaiThac(ThongKeDoanhNghiepKhaiThac result, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Quyết định cấp phép; ngày, tháng, năm", "Cơ quan có thẩm quyền cấp giấy phép", "Doanh nghiệp khai thác", "Tên, ví trí khu vực mỏ", "Loại khoáng sản", "Diện tích (ha)", "Trữ lượng mỏ/công suất khai thác (m3, tấn)", "Thời hạn cấp phép"};
        final String reportName = "DANH SÁCH DOANH NGHIỆP KHAI THÁC KHOÁNG SẢN TRÊN ĐỊA BÀN" + result.getDiaBan();
        XSSFWorkbook workbook = initWorkBook(reportName, columnNames, ngayDau, ngayCuoi, true);
        XSSFSheet sheet = workbook.getSheet("0");
        // write data
        {
            int rowNum = 3;
            for (Map.Entry<Integer, List<String[]>> t : result.getDataForm().entrySet()) {
                //Row row = row
                Row rowThamQuyenCap = sheet.createRow(rowNum++);
                Cell cellThamQuyenCap = rowThamQuyenCap.createCell(0, CellType.STRING);
                cellThamQuyenCap.setCellValue(ThamQuyenCap.getThamQuyenCap(t.getKey()));
                sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, columnNames.length - 1));


                for (String[] d : t.getValue()) {
                    Row rowData = sheet.createRow(rowNum++);
                    for (int i = 0; i < columnNames.length; i++) {
                        Cell cell = rowData.createCell(i, CellType.STRING);
                        cell.setCellValue(d[i]);
                    }
                }
            }
        }
        for (int i = 0; i < columnNames.length; i++) sheet.autoSizeColumn(i);
        return workbook;
    }

    public static XWPFDocument createDocxThongKeDoanhNghiepKhaiThac(ThongKeDoanhNghiepKhaiThac result, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Quyết định cấp phép; ngày, tháng, năm", " Cơ quan có thẩm quyền cấp giấy phép", "Doanh nghiệp khai thác", "Tên, ví trí khu vực mỏ", "Loại khoáng sản", "Diện tích (ha)", "Trữ lượng mỏ/công suất khai thác (m3, tấn)", "Thời hạn cấp phép"};
        final String reportName = "DANH SÁCH DOANH NGHIỆP KHAI THÁC KHOÁNG SẢN TRÊN ĐỊA BÀN" + result.getDiaBan();

        XWPFDocument document = initDocument(reportName, ngayDau, ngayCuoi, true);
        XWPFTable table = document.createTable(1, columnNames.length);

        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < columnNames.length; i++) headerRow.getCell(i).setText(columnNames[i]);

        for (Map.Entry<Integer, List<String[]>> t : result.getDataForm().entrySet()) {
            //Row row = row
            XWPFTableRow rowThamQuyenCap = table.createRow();
            rowThamQuyenCap.getCell(1).setText(ThamQuyenCap.getThamQuyenCap(t.getKey()));
            rowThamQuyenCap.getCell(1).setColor("FF0000");

            for (String[] d : t.getValue()) {
                XWPFTableRow rowData = table.createRow();
                for (int i = 0; i < columnNames.length; i++) {
                    rowData.getCell(i).setText(d[i]);
                }
            }
        }
        return document;
    }

    public static String createHtmlThongKeDoanhNghiepKhaiThac(ThongKeDoanhNghiepKhaiThac result, Date ngayDau, Date ngayCuoi) throws IOException {
        final String[] columnNames = {"STT", "Quyết định cấp phép; ngày, tháng, năm", " Cơ quan có thẩm quyền cấp giấy phép", "Doanh nghiệp khai thác", "Tên, ví trí khu vực mỏ", "Loại khoáng sản", "Diện tích (ha)", "Trữ lượng mỏ/công suất khai thác (m3, tấn)", "Thời hạn cấp phép"};
        final String reportName = "DANH SÁCH DOANH NGHIỆP KHAI THÁC KHOÁNG SẢN TRÊN ĐỊA BÀN" + result.getDiaBan();
        String htmlString = initHtml(reportName, columnNames, ngayDau, ngayCuoi, true);
        StringBuilder dataRows = new StringBuilder("");
        for (Map.Entry<Integer, List<String[]>> t : result.getDataForm().entrySet()) {
            // row Tham Quyen Cap
            dataRows.append("<tr><td colspan=\""+ columnNames.length +"\">" + ThamQuyenCap.getThamQuyenCap(t.getKey()) + "</td></tr>");
            // row Giay Phep Khai Thac
            for (String[] d : t.getValue()) {
                dataRows.append("<tr>");
                for (int i = 0; i < columnNames.length; i++) dataRows.append("<td>" + d[i] + "</td>");
                dataRows.append("</tr>");
            }
        }
        htmlString = StringUtils.replace(htmlString, "$dataRows", dataRows.toString());
        return htmlString;
    }

    public static XSSFWorkbook createExcelThongKeNhatKyXe(List<NhatKyXeVaoMo> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Biển số xe", "Mỏ", "Ngày giờ vào", "Ngày giờ ra", "Trọng lượng khoáng sản"};
        final String reportName = "DANH SÁCH XE VẬN CHUYỂN KHOÁNG SẢN";
        XSSFWorkbook workbook = initWorkBook(reportName, columnNames, ngayDau, ngayCuoi, false);
        XSSFSheet sheet = workbook.getSheet("0");
        // write data
        {
            int rowNum = 3;

            for (NhatKyXeVaoMo t : resultList) {
                //Row row = row
                Row row = sheet.createRow(rowNum++);
                //STT
                Cell cellSTT = row.createCell(0, CellType.STRING);
                cellSTT.setCellValue(rowNum - 3);
                //Bien so xe
                Cell cellBienSoXe = row.createCell(1, CellType.STRING);
                cellBienSoXe.setCellValue(t.getXeVanChuyen().getBienSoXe());
                //Mo
                Cell cellMo = row.createCell(2, CellType.STRING);
                cellMo.setCellValue(t.getMo().getDiaChi());
                //Ngay gio vao
                Cell cellThoiGian = row.createCell(3, CellType.STRING);
                cellThoiGian.setCellValue(DateTimeUtils.toDateTimeString(t.getThoiGianVao()));
                //Ngay gio ra
                Cell cellNgayGioRa = row.createCell(4, CellType.STRING);
                cellNgayGioRa.setCellValue(DateTimeUtils.toDateTimeString(t.getThoiGianRa()));
                //Trong luong khoang san
                Cell cellTrongLuongKhoangSan = row.createCell(5, CellType.NUMERIC);
                cellTrongLuongKhoangSan.setCellValue(t.getTrongLuongKhoangSan());
            }
        }
        for (int i = 0; i < columnNames.length; i++) sheet.autoSizeColumn(i);
        return workbook;
    }

    public static XWPFDocument createDocxThongKeNhatKyXe(List<NhatKyXeVaoMo> resultList, Date ngayDau, Date ngayCuoi) {
        final String[] columnNames = {"STT", "Biển số xe", "Mỏ", "Ngày giờ vào", "Ngày giờ ra", "Trọng lượng khoáng sản"};
        final String reportName = "DANH SÁCH XE VẬN CHUYỂN KHOÁNG SẢN";

        XWPFDocument document = initDocument(reportName, ngayDau, ngayCuoi, true);
        XWPFTable table = document.createTable(1, columnNames.length);

        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < columnNames.length; i++) headerRow.getCell(i).setText(columnNames[i]);

        int rowNum = 1;
        for (NhatKyXeVaoMo t : resultList) {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(String.valueOf(rowNum++));
            dataRow.getCell(1).setText(t.getXeVanChuyen().getBienSoXe());
            dataRow.getCell(2).setText(t.getMo().getDiaChi());
            dataRow.getCell(3).setText(DateTimeUtils.toDateTimeString(t.getThoiGianVao()));
            dataRow.getCell(4).setText(DateTimeUtils.toDateTimeString(t.getThoiGianRa()));
            dataRow.getCell(5).setText(String.valueOf(t.getTrongLuongKhoangSan()));
        }
        return document;
    }

    public static String createHtmlThongKeNhatKyXe(List<NhatKyXeVaoMo> resultList, Date ngayDau, Date ngayCuoi) throws IOException {
        final String[] columnNames = {"STT", "Biển số xe", "Mỏ", "Ngày giờ vào", "Ngày giờ ra", "Trọng lượng khoáng sản"};
        final String reportName = "DANH SÁCH XE VẬN CHUYỂN KHOÁNG SẢN";
        String htmlString = initHtml(reportName, columnNames, ngayDau, ngayCuoi, false);
        StringBuilder dataRows = new StringBuilder("");

        int rowNum = 1;
        for (NhatKyXeVaoMo t : resultList) {
            dataRows.append("<tr>");
            dataRows.append("<td>" + rowNum++ + "</td>");
            dataRows.append("<td>" + t.getXeVanChuyen().getBienSoXe() + "</td>");
            dataRows.append("<td>" + t.getXeVanChuyen().getBienSoXe() + "</td>");
            dataRows.append("<td>" + DateTimeUtils.toDateTimeString(t.getThoiGianVao()) + "</td>");
            dataRows.append("<td>" + DateTimeUtils.toDateTimeString(t.getThoiGianRa()) + "</td>");
            dataRows.append("<td>" + t.getTrongLuongKhoangSan() + "</td>");
            dataRows.append("</tr>");
        }
        htmlString = StringUtils.replace(htmlString, "$dataRows", dataRows.toString());
        return htmlString;
    }

}