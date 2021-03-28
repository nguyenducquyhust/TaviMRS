package com.tavi.tavi_mrs.controller.thong_ke;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeDoanhNghiepKhaiThac;
import com.tavi.tavi_mrs.service.giay_phep_khai_thac.GiayPhepKhaiThacService;
import com.tavi.tavi_mrs.service.khac.FileService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import com.tavi.tavi_mrs.utils.ReportUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/thong-ke/doanh-nghiep-khai-thac")
public class ThongKeDoanhNghiepKhaiThacController {

    @Autowired
    private GiayPhepKhaiThacService giayPhepKhaiThacService;

    @Autowired
    private FileService fileService;

    // Tong Hop Doanh Nghiep Khai Thac
    @GetMapping("/")
    public ResponseEntity<JsonResult> abstractReport(@RequestParam(value = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                     @RequestParam(value = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi,
                                                     @RequestParam(value = "khoang-san-ids") List<Integer> khoangSanIds,
                                                     @RequestParam(value = "huyen-id", defaultValue = "0", required = false) int huyenId) {
        return Optional.ofNullable(giayPhepKhaiThacService.abstractReport(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), khoangSanIds, huyenId))
                .map(giayPhepKhaiThacs -> !giayPhepKhaiThacs.isEmpty() ? JsonResult.found(giayPhepKhaiThacs) : JsonResult.notFound("DoanhNghiep/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PostMapping("/xuat-exel")
    public ResponseEntity<JsonResult> abstractReportAsExcel(@RequestBody ThongKeDoanhNghiepKhaiThac resultMap,
                                                           @RequestParam(value = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                           @RequestParam(value = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi) {
        XSSFWorkbook workbook = ReportUtils.createExcelThongKeDoanhNghiepKhaiThac(resultMap, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
        String fileName = "ThongKeDoanhNghiepKhaiThac" + LocalDateTime.now().getNano();
        return Optional.ofNullable(fileService.writeExcel(workbook, fileName))
                .map(JsonResult::uploaded)
                .orElse(JsonResult.badRequest("Create Excel fail"));
    }

    @PostMapping("/xuat-docx")
    public ResponseEntity<JsonResult> abstractReportAsDocx(@RequestBody ThongKeDoanhNghiepKhaiThac resultMap,
                                                           @RequestParam(value = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                           @RequestParam(value = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi) {
        XWPFDocument document = ReportUtils.createDocxThongKeDoanhNghiepKhaiThac(resultMap, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
        String fileName = "ThongKeDoanhNghiepKhaiThac" + LocalDateTime.now().getNano();
        return Optional.ofNullable(fileService.writeDocx(document, fileName))
                .map(JsonResult::uploaded)
                .orElse(JsonResult.badRequest("Create Word fail"));
    }

    @PostMapping("/xuat-pdf")
    public ResponseEntity<JsonResult> abstractReportAsPDF(@RequestBody ThongKeDoanhNghiepKhaiThac resultMap,
                                                          @RequestParam(value = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                          @RequestParam(value = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi) {
        try {
            String htmlString = ReportUtils.createHtmlThongKeDoanhNghiepKhaiThac(resultMap, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
            String fileName = "ThongKeDoanhNghiepKhaiThac" + LocalDateTime.now().getNano();
            fileService.writeHTML(htmlString, fileName);
            return Optional.ofNullable(fileService.convertHtmlToPDF(fileName, fileName))
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("Create PDF fail"));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.badRequest("Create PDF fail");
        }
    }

}
