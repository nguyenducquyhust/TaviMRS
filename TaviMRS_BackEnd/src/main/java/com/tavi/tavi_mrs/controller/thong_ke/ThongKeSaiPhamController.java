package com.tavi.tavi_mrs.controller.thong_ke;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import com.tavi.tavi_mrs.service.khac.FileService;
import com.tavi.tavi_mrs.service.vi_pham.ViPhamService;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/thong-ke/sai-pham")
public class ThongKeSaiPhamController {

    @Autowired
    private ViPhamService viPhamService;

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public ResponseEntity<JsonResult> baoCaoViPham(@RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                   @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                   @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                   @RequestParam(name = "loai-vi-pham", defaultValue = "0", required = false) int loaiViPhamId) {
        List<ViPham> listViPham = viPhamService.findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(doanhNghiepId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), loaiViPhamId);
        return Optional.ofNullable(listViPham)
                .map(JsonResult::found)
                .orElse(JsonResult.serverError("Internal Server error"));
    }


    @GetMapping("/xuat-excel")
    public ResponseEntity<JsonResult> abstractExcel(@RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                    @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                    @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                    @RequestParam(name = "loai-vi-pham", defaultValue = "0", required = false) int loaiViPhamId) {
        return Optional.ofNullable(viPhamService.findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(doanhNghiepId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), loaiViPhamId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XSSFWorkbook workbook = ReportUtils.createExcelThongKeViPham(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeSaiPham" + LocalDateTime.now().getNano();
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
    public ResponseEntity<JsonResult> abstractDocx(@RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                   @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                   @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                   @RequestParam(name = "loai-vi-pham", defaultValue = "0", required = false) int loaiViPhamId) {
        return Optional.ofNullable(viPhamService.findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(doanhNghiepId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), loaiViPhamId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XWPFDocument document = ReportUtils.createDocxThongKeViPham(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeSaiPham" + LocalDateTime.now().getNano();
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
    public ResponseEntity<JsonResult> abstractPDF(@RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                  @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                  @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                  @RequestParam(name = "loai-vi-pham", defaultValue = "0", required = false) int loaiViPhamId) {
        return Optional.ofNullable(viPhamService.findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(doanhNghiepId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), loaiViPhamId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        try {
                            String htmlString = ReportUtils.createHtmlThongKeViPham(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                            String fileName = "ThongKeSaiPham" + LocalDateTime.now().getNano();
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

