package com.tavi.tavi_mrs.controller.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import com.tavi.tavi_mrs.service.xe_van_chuyen.XeVanChuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/xe-van-chuyen")
public class XeVanChuyenController {

    @Autowired
    private XeVanChuyenService xeVanChuyenService;

    @Autowired
    private DoanhNghiepService doanhNghiepService;


    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("xe-van-chuyen-id") int xeVanChuyenId) {
        return xeVanChuyenService.findByIdXeVanChuyenAndXoa(xeVanChuyenId, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<XeVanChuyen> xeVanChuyenPage = xeVanChuyenService.findAll(pageable);

        return Optional.ofNullable(xeVanChuyenPage)
                .map(xeVanChuyens -> xeVanChuyens.getTotalElements() != 0 ? JsonResult.found(PageJson.build(xeVanChuyens)) : JsonResult.notFound("XeVanChuyen/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-doanh-nghiep")
    public ResponseEntity<JsonResult> findByDoanhNghiep(@RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                                        @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                        @RequestParam(value = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<XeVanChuyen> xeVanChuyenPage = xeVanChuyenService.findByDoanhNghiepId(doanhNghiepId, pageable);
        return Optional.ofNullable(xeVanChuyenPage)
                .map(mos -> mos.getTotalElements() != 0 ? JsonResult.found(PageJson.build(mos)) : JsonResult.notFound("DoanhNghiep"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-trang-thai")
    public ResponseEntity<JsonResult> findByTrangThai(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                        @RequestParam(value = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<XeVanChuyen> xeVanChuyenPage = xeVanChuyenService.findByTrangThai( pageable);
        return Optional.ofNullable(xeVanChuyenPage)
                .map(mos -> mos.getTotalElements() != 0 ? JsonResult.found(PageJson.build(mos)) : JsonResult.notFound("TrangThai"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search-xe-cho-duyet")
    public ResponseEntity<JsonResult> searchXCD(
            @RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
            @RequestParam(name = "bien-so-xe", defaultValue = "", required = false) String bienSoXe,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<XeVanChuyen> xeVanChuyenPage = xeVanChuyenService.findByTrangThaiAndDoanhNghiepAndBienSoXe( doanhNghiepId, bienSoXe, pageable);
        return Optional.ofNullable(xeVanChuyenPage)
                .map(xeVanChuyens -> xeVanChuyens.getTotalElements() != 0 ? JsonResult.found(PageJson.build(xeVanChuyens)) : JsonResult.notFound("DoanhNghiep/BienSoXe/TrangThai"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }


    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                             @RequestBody XeVanChuyen xeVanChuyen) {

        return doanhNghiepService.findByIdDoanhNghiepAndXoa(doanhNghiepId, false)
                .map(doanhNghiep -> {
                    xeVanChuyen.setDoanhNghiep(doanhNghiep);
                    return xeVanChuyenService.save(xeVanChuyen)
                            .map(JsonResult::uploaded)
                            .orElse(JsonResult.saveError("Save XeVanChuyen Error"));
                })
                .orElse(JsonResult.parentNotFound("Save Doanh Nghiep Error"));

    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@RequestBody XeVanChuyen xeVanChuyen) {
        return xeVanChuyenService.save(xeVanChuyen)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("XeVanCHuyen"));
    }

    @GetMapping("/search")
    ResponseEntity<JsonResult> search(@RequestParam(value = "bien-so-xe", defaultValue = "", required = false) String bienSoXe,
                                      @RequestParam(value = "trang-thai", required = false, defaultValue = "-1") int trangThai,
                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page-1,size);
        if(bienSoXe == "" && trangThai == -1){
            Page<XeVanChuyen> xeVanChuyenPage = xeVanChuyenService.findAll( pageable);
        }
        Page<XeVanChuyen> xeVanChuyenPage = xeVanChuyenService.findByBienSoXeAndTrangThai(bienSoXe, trangThai, pageable);

        return Optional.ofNullable(xeVanChuyenPage)
                .map(xeVanChuyens -> xeVanChuyens.getTotalElements() != 0 ? JsonResult.found(PageJson.build(xeVanChuyens)) : JsonResult.notFound("DoanhNghiep/BienSoXe/TrangThai"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}
