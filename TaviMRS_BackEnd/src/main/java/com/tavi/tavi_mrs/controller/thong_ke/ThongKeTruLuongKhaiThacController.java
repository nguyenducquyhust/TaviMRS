package com.tavi.tavi_mrs.controller.thong_ke;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.service.khac.FileService;
import com.tavi.tavi_mrs.service.mo.NhatKyKhaiThacService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import com.tavi.tavi_mrs.utils.ReportUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/thong-ke/tru-luong-khai-thac")
public class ThongKeTruLuongKhaiThacController {

    @Autowired
    private NhatKyKhaiThacService nhatKyKhaiThacService;

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public ResponseEntity<JsonResult> abstractThongKeTruLuongKhaiThac(@RequestParam(name = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                                      @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi,
                                                                      @RequestParam(name = "khoang-san-ids", required = false) List<Integer> khoangSanIds,
                                                                      @RequestParam(name = "huyen-id", defaultValue = "0", required = false) int huyenId) {
        return Optional.ofNullable(nhatKyKhaiThacService.abstractThongKeTruLuongKhaiThac(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), khoangSanIds, huyenId))
                .map(resultList -> !resultList.isEmpty() ? JsonResult.found(resultList) : JsonResult.notFound("KhoangSan/Huyen"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @GetMapping("/xuat-excel")
    public ResponseEntity<JsonResult> abstractExcel(@RequestParam(name = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                    @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi,
                                                    @RequestParam(name = "khoang-san-ids", required = false) List<Integer> khoangSanIds,
                                                    @RequestParam(name = "huyen-id", defaultValue = "0", required = false) int huyenId) {
        return Optional.ofNullable(nhatKyKhaiThacService.abstractThongKeTruLuongKhaiThac(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), khoangSanIds, huyenId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XSSFWorkbook workbook = ReportUtils.createExcelThongKeTruLuongKhaiThac(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeTruLuongKhaiThac" + LocalDateTime.now().getNano();
                        return Optional.ofNullable(fileService.writeExcel(workbook, fileName))
                                .map(JsonResult::uploaded)
                                .orElse(JsonResult.badRequest("Create Excel fail"));
                    } else {
                        return JsonResult.notFound("No data to convert");
                    }
                })
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @GetMapping("/xuat-word")
    public ResponseEntity<JsonResult> abstractDocx(@RequestParam(name = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                   @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi,
                                                   @RequestParam(name = "khoang-san-ids", required = false) List<Integer> khoangSanIds,
                                                   @RequestParam(name = "huyen-id", defaultValue = "0", required = false) int huyenId) {
        return Optional.ofNullable(nhatKyKhaiThacService.abstractThongKeTruLuongKhaiThac(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), khoangSanIds, huyenId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XWPFDocument document = ReportUtils.createDocxThongKeTruLuongKhaiThac(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeTruLuongKhaiThac" + LocalDateTime.now().getNano();
                        return Optional.ofNullable(fileService.writeDocx(document, fileName))
                                .map(JsonResult::uploaded)
                                .orElse(JsonResult.badRequest("Create Word fail"));
                    } else {
                        return JsonResult.notFound("No data to convert");
                    }
                })
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @GetMapping("/xuat-pdf")
    public ResponseEntity<JsonResult> abstractPdf(@RequestParam(name = "ngay-dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                  @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi,
                                                  @RequestParam(name = "khoang-san-ids", required = false) List<Integer> khoangSanIds,
                                                  @RequestParam(name = "huyen-id", defaultValue = "0", required = false) int huyenId) {
        return Optional.ofNullable(nhatKyKhaiThacService.abstractThongKeTruLuongKhaiThac(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), khoangSanIds, huyenId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        try {
                            String htmlString = ReportUtils.createHtmlThongKeTruLuongKhaiThac(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                            String fileName = "ThongKeTruLuongKhaiThac" + LocalDateTime.now().getNano();
                            fileService.writeHTML(htmlString, fileName);
                            return Optional.ofNullable(fileService.convertHtmlToPDF(fileName, fileName))
                                    .map(JsonResult::uploaded)
                                    .orElse(JsonResult.saveError("Create PDF fail"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return JsonResult.badRequest("Create PDF fail");
                    } else {
                        return JsonResult.notFound("No data to convert");
                    }
                })
                .orElse(JsonResult.serverError("Internal Server Error"));
    }
}

