package com.tavi.tavi_mrs.controller.vi_pham;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import com.tavi.tavi_mrs.service.mo.MoService;
import com.tavi.tavi_mrs.service.thong_bao.ThongBaoService;
import com.tavi.tavi_mrs.service.vi_pham.LoaiViPhamService;
import com.tavi.tavi_mrs.service.vi_pham.ViPhamService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/vi-pham")
public class ViPhamController {

    @Autowired
    private ViPhamService viPhamService;

    @Autowired
    private MoService moService;

    @Autowired
    private LoaiViPhamService loaiViPhamService;

    @Autowired
    private ThongBaoService thongBaoService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("vi-pham-id") int viPhamId) {
        return viPhamService.findByIdViPhamAndXoa(viPhamId, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-by-trang-thai-xu-ly")
    public ResponseEntity<JsonResult> findByTrangThaiXuLy(@RequestParam("trang-thai-xu-ly") int trangThaiXuLy) {
        return Optional.ofNullable(viPhamService.findByTrangThaiXuLy(trangThaiXuLy))
                .map(viPhams -> !viPhams.isEmpty() ? JsonResult.found(viPhams) : JsonResult.notFound("TrangThaiXuLy"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByTrangThaiXuLy(@RequestParam("mo-id") Integer moId,
                                                          @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                          @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return Optional.ofNullable(viPhamService.findByMo(moId, pageable))
                .map(resultPage -> resultPage.getTotalElements() != 0 ? JsonResult.found(PageJson.build(resultPage)) : JsonResult.notFound("Mo/Page"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @GetMapping("/find-by-mo-trang-thai-xu-ly")
    public ResponseEntity<JsonResult> findByMo(@RequestParam(value = "mo-id", defaultValue = "0", required = false) Integer moId,
                                               @RequestParam(value = "trang-thai-xu-ly", defaultValue = "0", required = false) Integer trangThaiXuLy) {
        return Optional.ofNullable(viPhamService.findByMoAndTrangThaiXuLy(moId, trangThaiXuLy))
                .map(viPhams -> !viPhams.isEmpty() ? JsonResult.found(viPhams) : JsonResult.notFound("Mo"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResult> search(@RequestParam(name ="mo-id", defaultValue = "0", required = false) int moId,
                                             @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                             @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                             @RequestParam(name = "loai-vi-pham", defaultValue = "0", required = false) int loaiViPhamId,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ViPham> viPhamPage = viPhamService.findByMoAndThoiGianTaoAndLoaiViPham(moId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi),loaiViPhamId, pageable);
        return Optional.ofNullable(viPhamPage)
                .map(viPhams -> viPhams.getTotalElements() != 0 ? JsonResult.found(PageJson.build(viPhams)) : JsonResult.notFound("ViPham/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestParam("loai-vi-pham-id") int loaiViPhamId,
                                             @RequestParam("mo-id") int moId,
                                             @RequestBody ViPham viPham) {
        return loaiViPhamService.findByIdLoaiViPhamAndXoa(loaiViPhamId, false)
                .map(loaiViPham -> {
                    viPham.setLoaiViPham(loaiViPham);
                                return moService.findByIdMoAndXoa(moId, false)
                                        .map(mo -> {
                                            viPham.setMo(mo);
                                            viPham.setDoanhNghiep(mo.getDoanhNghiep());
                                            return viPhamService.save(viPham)
                                                    .map(JsonResult::uploaded)
                                                    .orElse(JsonResult.saveError("ViPham "));
                                        })
                                        .orElse(JsonResult.parentNotFound("Mo/DoanhNghiep "));
                })
                .orElse(JsonResult.parentNotFound("LoaiViPham "));
    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@Valid @RequestBody ViPham viPham) {
        return viPhamService.save(viPham)
                .map(JsonResult::found)
                .orElse(JsonResult.saveError("ViPham "));
    }
}
