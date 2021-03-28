package com.tavi.tavi_mrs.controller.mo;

import com.tavi.tavi_mrs.entities.common.TrangThaiHoatDongMo;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.json.PageJsonAlt;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.service.cms.CMSService;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import com.tavi.tavi_mrs.service.khoang_san.KhoangSanService;
import com.tavi.tavi_mrs.service.mo.MoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin/mo")
public class MoController {

    @Autowired
    private CMSService cmsService;

    @Autowired
    private MoService moService;

    @Autowired
    private DoanhNghiepService doanhNghiepService;

    @Autowired
    private KhoangSanService khoangSanService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("mo-id") int idMo) {
        return moService.findByIdMoAndXoa(idMo, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Mo> moPage = moService.findAllToPage(pageable);

        return Optional.ofNullable(moPage)
                .map(mos -> mos.getTotalElements() != 0 ? JsonResult.found(PageJson.build(mos)) : JsonResult.notFound("Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-trang-thai-hoat-dong")
    public ResponseEntity<JsonResult> findByTrangThaiHoatDong(@RequestParam(name = "trang-thai-hoat-dong", defaultValue = "0", required = false) int trangThaiHoatDong) {
        if (TrangThaiHoatDongMo.isTrangThaiHoatDongMo(trangThaiHoatDong)) {
            return Optional.ofNullable(moService.findByTrangThaiHoatDong(trangThaiHoatDong))
                    .map(resultList -> !resultList.isEmpty() ? JsonResult.found(resultList) : JsonResult.notFound("Mo/TrangThaiHoatDong"))
                    .orElse(JsonResult.serverError("Internal Server Error"));
        } return JsonResult.badRequest("Invalid TrangThaiHoatDong");
    }

    @GetMapping("/find-by-doanh-nghiep")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
                                              @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Mo> moPage = moService.findByDoanhNghiepId(doanhNghiepId, pageable);

        return Optional.ofNullable(moPage)
                .map(mos -> mos.getTotalElements() != 0 ? JsonResult.found(PageJson.build(mos)) : JsonResult.notFound("DoanhNghiep/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResult> search(@RequestParam(value = "khoang-san-ids", required = false) List<Integer> khoangSanIds,
                                             @RequestParam(value = "doanh-nghiep-id", defaultValue = "0", required = false) int idDoanhNghiep,
                                             @RequestParam(value = "dia-chi", defaultValue = "%", required = false) String diaChi,
                                             @RequestParam(value = "tinh-id", defaultValue = "0", required = false) int tinhId,
                                             @RequestParam(value = "huyen-id", defaultValue = "0", required = false) int huyenId,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Mo> moPage = moService.findByKhoangSanIdsAndDoanhNghiepIdAndDiaChiAndTinhIdAndHuyenId(khoangSanIds, idDoanhNghiep, diaChi, tinhId, huyenId, pageable);

        return Optional.ofNullable(moPage)
                .map(mos -> mos.getTotalElements() != 0 ? JsonResult.found(PageJson.build(mos)) : JsonResult.notFound("KhoangSan/DoanhNghiep/DiaChi/Tinh/Huyen"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-khoang-san-and-thong-tin-doanh-nghiep")
    public ResponseEntity<JsonResult> findByKhoangSanAndThongTinDoanhNghiep(@RequestParam(value = "khoang-san-ids", required = false) List<Integer> khoangSanIds,
                                                                            @RequestParam(value = "ten", defaultValue = "%", required = false) String ten,
                                                                            @RequestParam(value = "huyen-id", defaultValue = "0", required = false) int huyenId,
                                                                            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                                            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return Optional.ofNullable(moService.findPageByKhoangSanIdsAndThongTinDoanhNghiep(khoangSanIds, ten, huyenId, pageable))
                .map(rsPage
                        -> rsPage.getTotalElements() != 0
                        ? JsonResult.found(new PageJsonAlt<>(rsPage.stream().collect(Collectors.groupingBy(Mo::getDoanhNghiep)),
                                                             rsPage.getTotalPages(),
                                                             rsPage.getTotalElements()))
                        : JsonResult.notFound("KhoangSan/TenDoanhNghiep/HuyenId") )
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestParam("doanh-nghiep-id") int idDoanhNghiep,
                                             @RequestParam("khoang-san-id") int idKhoangSan,
                                             @RequestBody Mo mo) {
        return doanhNghiepService.findByIdDoanhNghiepAndXoa(idDoanhNghiep, false)
                .map(doanhNghiep -> {
                    mo.setDoanhNghiep(doanhNghiep);
                    return khoangSanService.findByIdKhoangSanAndXoa(idKhoangSan, false)
                            .map(khoangSan -> {
                                mo.setKhoangSan(khoangSan);
                                return moService.save(mo)
                                        .map(JsonResult::uploaded)
                                        .orElse(JsonResult.saveError("Mo"));
                            })
                            .orElse(JsonResult.parentNotFound("KhoangSan"));
                })
                .orElse(JsonResult.parentNotFound("DoanhNghiep"));
    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@Valid @RequestBody Mo mo) {
        return moService.save(mo)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("Mo"));
    }
}
