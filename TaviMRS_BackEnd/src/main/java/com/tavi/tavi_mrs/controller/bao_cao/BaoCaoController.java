package com.tavi.tavi_mrs.controller.bao_cao;


import com.tavi.tavi_mrs.entities.bao_cao.BaoCao;
import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.service.bao_cao.BaoCaoService;
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
@RequestMapping("api/v1/admin/bao-cao")
public class BaoCaoController {
    @Autowired
    private BaoCaoService baoCaoService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("id") int idBaoCao) {
        return baoCaoService.findByIdBaoCaoAndXoa(idBaoCao, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BaoCao> baoCaos = baoCaoService.findAllToPage(pageable);

        return Optional.ofNullable(baoCaos)
                .map(bcs -> bcs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(bcs)) : JsonResult.notFound("BaoCao/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResult> findByTieuDeAndThoiGian(@RequestParam(name ="tieu-de", defaultValue = "", required = false) String tieuDe,
                                                            @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                            @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        System.out.println("from " + DateTimeUtils.asDate(ngayDau) + " to " + DateTimeUtils.asDate(ngayCuoi));
        System.out.println("from " + ngayDau + " to " + ngayCuoi);
        Page<BaoCao> baoCaos = baoCaoService.findByTieuDeAndThoiGian(tieuDe, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), pageable);
        return Optional.ofNullable(baoCaos)
                .map(bcs -> bcs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(bcs)) : JsonResult.notFound("BaoCao/TieuDe/ThoiGian"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search-doanh-nghiep")
    public ResponseEntity<JsonResult> findByTieuDeAndThoiGian(@RequestParam(name ="tieu-de", defaultValue = "", required = false) String tieuDe,
                                                              @RequestParam(name ="doanh-nghiep", defaultValue = "", required = false) String doanhNghiep,
                                                              @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                              @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                              @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        System.out.println("from " + DateTimeUtils.asDate(ngayDau) + " to " + DateTimeUtils.asDate(ngayCuoi));
        System.out.println("from " + ngayDau + " to " + ngayCuoi);
        Page<BaoCao> baoCaos = baoCaoService.findByTieuDeAndDoanhNghiepAndThoiGian(tieuDe, doanhNghiep, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), pageable);
        return Optional.ofNullable(baoCaos)
                .map(bcs -> bcs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(bcs)) : JsonResult.notFound("BaoCao/TieuDe/ThoiGian"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-thong-bao")
    public ResponseEntity<JsonResult> findByThongBao(@RequestParam("thong-bao-id") int thongBaoId,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BaoCao> baoCaoPage = baoCaoService.findByThongBao(thongBaoId, pageable);
        return Optional.ofNullable(baoCaoPage)
                .map(bcs -> bcs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(bcs)) : JsonResult.notFound("BaoCao/ThongBao"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}
