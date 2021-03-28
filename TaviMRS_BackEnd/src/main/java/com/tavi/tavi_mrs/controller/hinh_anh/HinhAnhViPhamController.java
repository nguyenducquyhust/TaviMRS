package com.tavi.tavi_mrs.controller.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhViPham;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.service.hinh_anh.HinhAnhViPhamService;
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
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/admin/hinh-anh-vi-pham")
public class HinhAnhViPhamController {

    @Autowired
    private HinhAnhViPhamService hinhAnhViPhamService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("id") int idHinhAnhViPham) {
        return hinhAnhViPhamService.findByIdHinhAnhViPham(idHinhAnhViPham)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-by-vi-pham")
    public ResponseEntity<JsonResult> findByViPhamId(@RequestParam("vi-pham-id") int viPhamId,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HinhAnhViPham> hinhAnhViPhamPage = hinhAnhViPhamService.findByViPhamId(viPhamId,pageable);
        return Optional.ofNullable(hinhAnhViPhamPage)
                .map(hinhAnhViPhams -> hinhAnhViPhams.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhViPhams)) : JsonResult.notFound("HinhAnhViPham/ViPham"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-vi-pham-all")
    public ResponseEntity<JsonResult> findByViPhamAll(@RequestParam("vi-pham-id") int viPhamId) {
        return Optional.ofNullable(hinhAnhViPhamService.findByViPhamAll(viPhamId))
                .map(hinhAnhViPhams -> hinhAnhViPhams.isEmpty() ? JsonResult.notFound("vi pham") : JsonResult.found(hinhAnhViPhams))
                .orElse(JsonResult.serverError("server error"));
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResult> findByMo(@RequestParam(name = "vi-pham-id", defaultValue = "0", required = false) int viPhamId,
                                               @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                               @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        System.out.println("from " + DateTimeUtils.asDate(ngayDau) + " to " + DateTimeUtils.asDate(ngayCuoi));
        System.out.println("from " + ngayDau + " to " + ngayCuoi);
        Page<HinhAnhViPham> hinhAnhViPhamPage = hinhAnhViPhamService.findByViPhamAndThoiGian(viPhamId,DateTimeUtils.asDate(ngayDau),DateTimeUtils.asDate(ngayCuoi),pageable);
        return Optional.ofNullable(hinhAnhViPhamPage)
                .map(hinhAnhViPhams -> hinhAnhViPhams.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhViPhams)) : JsonResult.notFound("HinhAnhViPham/ViPham/ThoiGian"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }


    @GetMapping("/find-all-sort")
    public ResponseEntity<JsonResult> findAllSort(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                  @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                  @RequestParam(name = "sort", defaultValue = "false", required = false) boolean sort) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HinhAnhViPham> hinhAnhViPhamPage = hinhAnhViPhamService.findAllOrderByThoiGian(pageable, sort);
        return Optional.ofNullable(hinhAnhViPhamPage)
                .map(hinhAnhViPhams -> hinhAnhViPhams.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhViPhams)) : JsonResult.notFound("HinhAnhViPham/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search-doanh-nghiep-sort")
    public ResponseEntity<JsonResult> findByMoIdAndThoiGianSort(@RequestParam(name ="doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                                                @RequestParam(name ="loai-vi-pham-id", defaultValue = "0", required = false) int loaiViPhamId,
                                                                @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                                @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                                @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                                @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                                @RequestParam(name = "sort", defaultValue = "false", required = false) boolean sort){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HinhAnhViPham> hinhAnhViPhamPage = hinhAnhViPhamService.findByDoanhNghiepAndLoaiViPhamAndThoiGianOrderByThoiGian(doanhNghiepId, loaiViPhamId, DateTimeUtils.asDate(ngayDau),DateTimeUtils.asDate(ngayCuoi),pageable, sort);
        return Optional.ofNullable(hinhAnhViPhamPage)
                .map(hinhAnhViPhams -> hinhAnhViPhams.getTotalElements() != 0 ? JsonResult.found(PageJson.build(hinhAnhViPhams)) : JsonResult.notFound("HinhAnhViPham/DoanhNghiep/LoaiViPham/ViPham/ThoiGian"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

}
