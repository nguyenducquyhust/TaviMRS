package com.tavi.tavi_mrs.controller.nguoi_dung;


import com.tavi.tavi_mrs.entities.common.TrangThaiNguoiDung;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import com.tavi.tavi_mrs.service.mo.MoService;
import com.tavi.tavi_mrs.service.nguoi_dung.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin/nguoi-dung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private DoanhNghiepService doanhNghiepService;

    @Autowired
    private MoService moService;

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByMo(@RequestParam("mo-id") int moId,
                                               @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(value = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<NguoiDung> nguoiDungPage = nguoiDungService.findByMoId(moId, pageable);
        return Optional.ofNullable(nguoiDungPage)
                .map(nguoiDungs -> nguoiDungs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(nguoiDungs)) : JsonResult.notFound("MO/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-all")
    ResponseEntity<JsonResult> findAllToPage(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NguoiDung> nguoiDungs = nguoiDungService.findAll(pageable);
        return Optional.ofNullable(nguoiDungs)
                .map(has -> has.getTotalElements() != 0 ? JsonResult.found(PageJson.build(has)) : JsonResult.notFound("Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-all-group-doanh-nghiep")
    ResponseEntity<JsonResult> findAllGroupDoanhNghiep() {
        List<NguoiDung> nguoiDungs = nguoiDungService.findAll();
        return Optional.ofNullable(nguoiDungs)
                .map(has -> has.size() != 0 ? JsonResult.found(has.stream().collect(Collectors.groupingBy(NguoiDung::getDoanhNghiep)).values()) : JsonResult.notFound("nguoidung"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("nguoi-dung-id") int nguoiDungId) {
        return nguoiDungService.findByIdNguoiDungAndXoa(nguoiDungId, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@RequestBody NguoiDung nguoiDung) {
        return nguoiDungService.save(nguoiDung)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("NguoiDung"));
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                             @RequestBody NguoiDung nguoiDung) {
        nguoiDung.setTrangThai(TrangThaiNguoiDung.HOAT_DONG);
        nguoiDung.setXoa(false);
        return doanhNghiepService.findByIdDoanhNghiepAndXoa(doanhNghiepId, false)
                .map(doanhNghiep -> {
                    nguoiDung.setDoanhNghiep(doanhNghiep);
                    return nguoiDungService.save(nguoiDung)
                            .map(JsonResult::uploaded)
                            .orElse(JsonResult.saveError("Save NguoiDung Error"));
                })
                .orElse(JsonResult.parentNotFound("Save Doanh Nghiep Error"));
    }

    @GetMapping("/find-by-doanh-nghiep")
    public ResponseEntity<JsonResult> findByDoanhNghiep(@RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                                        @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                        @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<NguoiDung> nguoiDungPage = nguoiDungService.findByDoanhNghiepId(doanhNghiepId, pageable);
        return Optional.ofNullable(nguoiDungPage)
                .map(nguoiDungs -> nguoiDungs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(nguoiDungs)) : JsonResult.notFound("DoanhNghiep/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

//    @GetMapping("/search")
//    ResponseEntity<JsonResult> search(@RequestParam(value = "mo-id", defaultValue = "0", required = false) int moId,
//                                      @RequestParam(value = "chuc-vu", defaultValue = "%", required = false) String chucVu,
//                                      @RequestParam(value = "trang-thai", defaultValue = "0", required = false) int trangThai,
//                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
//                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size){
//        Pageable pageable = PageRequest.of(page-1,size);
//        Page<NguoiDung> nguoiDungPage = nguoiDungService.findByMoIdAndChucVuAndTrangThai(moId, chucVu, trangThai, pageable);
//
//        return Optional.ofNullable(nguoiDungPage)
//                .map(nguoiDungs -> nguoiDungs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(nguoiDungs)) : JsonResult.notFound("Mo/ChucVu/TrangThai"))
//                .orElse(JsonResult.serverError("Internal Server error"));
//    }

    @GetMapping("/search")
    ResponseEntity<JsonResult> search(@RequestParam(value = "mo-id", defaultValue = "0", required = false) int moId,
                                      @RequestParam(value = "chuc-vu", defaultValue = "%", required = false) String chucVu,
                                      @RequestParam(value = "ten-dang-nhap", defaultValue = "%", required = false) String tenDangNhap,
                                      @RequestParam(value = "so-dien-thoai", defaultValue = "%", required = false) String soDienThoai,
                                      @RequestParam(value = "trang-thai", defaultValue = "0", required = false) int trangThai,
                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<NguoiDung> nguoiDungPage = nguoiDungService.findByMoIdAndTenDangNhapAndSoDienThoaiAndChucVuAndTrangThai(moId, tenDangNhap, soDienThoai, chucVu, trangThai, pageable);

        return Optional.ofNullable(nguoiDungPage)
                .map(nguoiDungs -> nguoiDungs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(nguoiDungs)) : JsonResult.notFound("Mo/TenDangNhap/SoDienThoai/ChucVu/TrangThai"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search-tcnd")
    ResponseEntity<JsonResult> search(@RequestParam(value = "ten-dang-nhap", defaultValue = "%", required = false) String tenDangNhap,
                                      @RequestParam(value = "id-doanh-nghiep", defaultValue = "0", required = false) int idDoanhNghiep,
                                      @RequestParam(value = "trang-thai", defaultValue = "0", required = false) int trangThai,
                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<NguoiDung> nguoiDungPage = nguoiDungService.findByTaiKhoanAndDoanhNghiepAndTrangThai(tenDangNhap, idDoanhNghiep, trangThai, pageable);

        return Optional.ofNullable(nguoiDungPage)
                .map(nguoiDungs -> nguoiDungs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(nguoiDungs)) : JsonResult.notFound("TenDangNhap/DoanhNghiep/TrangThai"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}
