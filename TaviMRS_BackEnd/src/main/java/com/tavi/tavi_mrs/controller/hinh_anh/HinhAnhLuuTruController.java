package com.tavi.tavi_mrs.controller.hinh_anh;


import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import com.tavi.tavi_mrs.service.hinh_anh.HinhAnhLuuTruService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/hinh-anh-luu-tru")
public class HinhAnhLuuTruController {

    @Autowired
    private HinhAnhLuuTruService hinhAnhLuuTruService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("id") int idHinhAnh) {
        return hinhAnhLuuTruService.findByIdHinhAnhAndXoa(idHinhAnh, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<HinhAnhLuuTru> hinhAnhLuuTruPage = hinhAnhLuuTruService.findAllToPage(pageable);
        return Optional.ofNullable(hinhAnhLuuTruPage)
                .map(hinhAnhLuuTrus -> hinhAnhLuuTrus.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhLuuTrus)) : JsonResult.notFound("HinhAnhLuuTru/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-all-sort")
    public ResponseEntity<JsonResult> findAllSort(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                              @RequestParam(name = "sort", defaultValue = "false", required = false) boolean sort) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HinhAnhLuuTru> hinhAnhLuuTruPage = hinhAnhLuuTruService.findAllOrderByThoiGian(pageable, sort);
        return Optional.ofNullable(hinhAnhLuuTruPage)
                .map(hinhAnhLuuTrus -> hinhAnhLuuTrus.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhLuuTrus)) : JsonResult.notFound("HinhAnhLuuTru/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResult> findByMoIdAndThoiGian(@RequestParam(name ="mo-id", defaultValue = "0", required = false) int moId,
                                               @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                               @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HinhAnhLuuTru> hinhAnhLuuTruPage = hinhAnhLuuTruService.findByMoIdAndThoiGian(moId,DateTimeUtils.asDate(ngayDau),DateTimeUtils.asDate(ngayCuoi),pageable);
        return Optional.ofNullable(hinhAnhLuuTruPage)
                .map(hinhAnhLuuTrus -> hinhAnhLuuTrus.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhLuuTrus)) : JsonResult.notFound("HinhAnhLuuTru/MoId/ThoiGian"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search-doanh-nghiep-sort")
    public ResponseEntity<JsonResult> findByMoIdAndThoiGianSort(@RequestParam(name ="doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                            @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                            @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                                @RequestParam(name = "sort", defaultValue = "false", required = false) boolean sort){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HinhAnhLuuTru> hinhAnhLuuTruPage = hinhAnhLuuTruService.findByDoanhNghiepIdAndThoiGianOrderByThoiGian(doanhNghiepId,DateTimeUtils.asDate(ngayDau),DateTimeUtils.asDate(ngayCuoi),pageable, sort);
        return Optional.ofNullable(hinhAnhLuuTruPage)
                .map(hinhAnhLuuTrus -> hinhAnhLuuTrus.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhLuuTrus)) : JsonResult.notFound("HinhAnhLuuTru/DoanhNghiepId/ThoiGian"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByMo(@RequestParam("mo-id") int moId,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HinhAnhLuuTru> hinhAnhLuuTruPage = hinhAnhLuuTruService.findByMoId(moId,pageable);
        return Optional.ofNullable(hinhAnhLuuTruPage)
                .map(hinhAnhLuuTrus -> hinhAnhLuuTrus.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhLuuTrus)) : JsonResult.notFound("HinhAnhLuuTru/MoId"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}
