package com.tavi.tavi_mrs.controller.bieu_do;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.service.khoang_san.KhoangSanService;
import com.tavi.tavi_mrs.service.mo.NhatKyKhaiThacService;
import com.tavi.tavi_mrs.service.xe_van_chuyen.NhatKyXeVaoMoService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/bieu-do")
public class BieuDoController {

    @Autowired
    private NhatKyKhaiThacService nhatKyKhaiThacService;

    @GetMapping("/bieu-do-tong-hop")
    public ResponseEntity<JsonResult> abstractBieuDoTongHop(@RequestParam(name = "ngay-dau",  defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                            @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi) {

        return Optional.ofNullable(nhatKyKhaiThacService.abstractBieuDoByKhoangSan(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(bieuDos -> ! bieuDos.isEmpty() ? JsonResult.found(bieuDos) : JsonResult.notFound("BieuDo"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/bieu-do-by-khoang-san")
    public ResponseEntity<JsonResult> abstractBieuDoByKhoangSan(@RequestParam(name = "ngay-dau",  defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                                @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi) {

        return Optional.ofNullable(nhatKyKhaiThacService.abstractBieuDoByKhoangSan(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(bieuDos -> ! bieuDos.isEmpty() ? JsonResult.found(bieuDos) : JsonResult.notFound("BieuDo"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/bieu-do-by-doanh-nghiep")
    public ResponseEntity<JsonResult> abstractBieuDoByDoanhNghiep(@RequestParam(name = "ngay-dau",  defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                                @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi) {

        return Optional.ofNullable(nhatKyKhaiThacService.abstractBieuDoByDoanhNghiep(DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(bieuDos -> ! bieuDos.isEmpty() ? JsonResult.found(bieuDos) : JsonResult.notFound("BieuDo"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}

