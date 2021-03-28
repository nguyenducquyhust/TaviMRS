package com.tavi.tavi_mrs.controller.thong_ke;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.service.khac.FileService;
import com.tavi.tavi_mrs.service.xe_van_chuyen.NhatKyXeVaoMoService;
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
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/thong-ke/luong-xe-vao-mo")
public class ThongKeTheoLuongXeVaoMoController {

    @Autowired
    private NhatKyXeVaoMoService nhatKyXeVaoMoService;

    @Autowired
    private FileService fileService;

    @GetMapping("/bao-cao")
    public ResponseEntity<JsonResult> abstractThongKeTheoLuongXeVaoMo(@RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                                      @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                                      @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {

        System.out.println("from " + DateTimeUtils.asDate(ngayDau) + " to " + DateTimeUtils.asDate(ngayCuoi));
        return Optional.ofNullable(nhatKyXeVaoMoService.abstractThongKeTheoLuongXeVaoMo(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), doanhNghiepId))
                .map(resultList -> !resultList.isEmpty() ? JsonResult.found(resultList) : JsonResult.notFound("DoanhNghiep"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }


    @GetMapping("/xuat-excel")
    public ResponseEntity<JsonResult> abstractExcel(@RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                    @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                    @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {
        return Optional.ofNullable(nhatKyXeVaoMoService.abstractThongKeTheoLuongXeVaoMo(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), doanhNghiepId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XSSFWorkbook workbook = ReportUtils.createExcelThongKeTheoLuongXeVaoMo(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeTheoLuongXeVaoMo" + LocalDateTime.now().getNano();
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
                                                    @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {
        return Optional.ofNullable(nhatKyXeVaoMoService.abstractThongKeTheoLuongXeVaoMo(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), doanhNghiepId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XWPFDocument document = ReportUtils.createDocxThongKeTheoLuongXeVaoMo(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeTheoLuongXeVaoMo" + LocalDateTime.now().getNano();
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
                                                  @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {
        return Optional.ofNullable(nhatKyXeVaoMoService.abstractThongKeTheoLuongXeVaoMo(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), doanhNghiepId))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        try {
                            String htmlString = ReportUtils.createHtmlThongKeTheoLuongXeVaoMo(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                            String fileName = "ThongKeTheoLuongXeVaoMo" + LocalDateTime.now().getNano();
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

