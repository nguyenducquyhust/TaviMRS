package com.tavi.tavi_mrs.controller.thong_ke;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import com.tavi.tavi_mrs.service.khac.FileService;
import com.tavi.tavi_mrs.service.vi_pham.ViPhamService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/thong-ke/nhat-ky-xe")
public class ThongKeNhatKyXeController {

    @Autowired
    private NhatKyXeVaoMoService nhatKyXeVaoMoService;

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public ResponseEntity<JsonResult> findByMoIdAndThoiGian(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
                                                            @RequestParam(name = "xe-id", defaultValue = "0", required = false) int xeId,
                                                            @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                            @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {
        return Optional.ofNullable(nhatKyXeVaoMoService.findByMoAndIdXeAndThoiGian(moId, xeId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(resultList -> !resultList.isEmpty() ? JsonResult.found(resultList) : JsonResult.notFound("Mo/Xe"))
                .orElse(JsonResult.serverError("Internal Server error"));

    }

    @GetMapping("/xuat-excel")
    public ResponseEntity<JsonResult> abstractExcel(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
                                                    @RequestParam(name = "xe-id", defaultValue = "0", required = false) int xeId,
                                                    @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                    @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {

        return Optional.ofNullable(nhatKyXeVaoMoService.findByMoAndIdXeAndThoiGian(moId, xeId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XSSFWorkbook workbook = ReportUtils.createExcelThongKeNhatKyXe(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeNhatKyXe" + LocalDateTime.now().getNano();
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
    public ResponseEntity<JsonResult> abstractDocx(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
                                                   @RequestParam(name = "xe-id", defaultValue = "0", required = false) int xeId,
                                                   @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                   @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {
        return Optional.ofNullable(nhatKyXeVaoMoService.findByMoAndIdXeAndThoiGian(moId, xeId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        XWPFDocument document = ReportUtils.createDocxThongKeNhatKyXe(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                        String fileName = "ThongKeNhatKyXe" + LocalDateTime.now().getNano();
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
    public ResponseEntity<JsonResult> abstractPDF(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
                                                  @RequestParam(name = "xe-id", defaultValue = "0", required = false) int xeId,
                                                  @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                  @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {
        return Optional.ofNullable(nhatKyXeVaoMoService.findByMoAndIdXeAndThoiGian(moId, xeId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(resultList -> {
                    if (!resultList.isEmpty()) {
                        try {
                            String htmlString = ReportUtils.createHtmlThongKeNhatKyXe(resultList, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi));
                            String fileName = "ThongKeNhatKyXe" + LocalDateTime.now().getNano();
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

