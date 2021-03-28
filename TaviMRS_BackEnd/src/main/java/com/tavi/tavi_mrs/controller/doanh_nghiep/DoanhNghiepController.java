package com.tavi.tavi_mrs.controller.doanh_nghiep;


import com.tavi.tavi_mrs.entities.common.TrangThaiDoanhNghiep;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/doanh-nghiep")
public class DoanhNghiepController {

    @Autowired
    private DoanhNghiepService doanhNghiepService;

    @GetMapping("/find-by-id")
    ResponseEntity<JsonResult> findById(@RequestParam("id") int idDoanhNghiep) {
        return doanhNghiepService.findByIdDoanhNghiepAndXoa(idDoanhNghiep, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-all")
    ResponseEntity<JsonResult> findAllToPage(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<DoanhNghiep> doanhNghiepPage = doanhNghiepService.findAll(pageable);
        return Optional.ofNullable(doanhNghiepPage)
                .map(doanhNghieps -> doanhNghieps.getTotalElements() != 0 ? JsonResult.found(PageJson.build(doanhNghieps)) : JsonResult.notFound("DoanhNghiep/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-thong-tin-doanh-nghiep")
    ResponseEntity<JsonResult> findAllByFilter() {
            return JsonResult.serverError("Server Error");
    }

    @PostMapping("/upload")
    ResponseEntity<JsonResult> upload(@RequestBody DoanhNghiep doanhNghiep) {
        //System.out.println(doanhNghiep);
        doanhNghiep.setXoa(false);
        doanhNghiep.setTrangThaiHoatDong(TrangThaiDoanhNghiep.HOAT_DONG);
        doanhNghiep.setTrangThaiPhanMem(TrangThaiDoanhNghiep.HOAT_DONG);
        return doanhNghiepService.save(doanhNghiep)
                .map(JsonResult::uploaded)
                .orElse(JsonResult.saveError("Upload Doanh Nghiep error"));
    }

    @PutMapping("/update")
    ResponseEntity<JsonResult> update(@Valid @RequestBody DoanhNghiep doanhNghiep) {
        return doanhNghiepService.save(doanhNghiep)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("Update Doanh Nghiep error"));
    }

    @PutMapping("/update-cau-hinh")
    ResponseEntity<JsonResult> updateCauHinh(@Valid @RequestBody DoanhNghiep doanhNghiep) {
        return doanhNghiepService.saveCauHinh(doanhNghiep)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("Update Doanh Nghiep error"));
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResult> search(@RequestParam(value = "ten-doanh-nghiep", defaultValue = "", required = false) String tenDoanhNghiep,
                                             @RequestParam(value = "dia-chi", defaultValue = "", required = false) String diaChi,
                                             @RequestParam(name = "trang-thai-phan-mem", defaultValue = "-1", required = false) int trangThaiPhanMem,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        if (tenDoanhNghiep == ""  && diaChi == "" && trangThaiPhanMem == -1 ) {
            Page<DoanhNghiep> doanhNghiepPage = doanhNghiepService.findAll(pageable);
        }
        Page<DoanhNghiep> doanhNghiepPage = doanhNghiepService.findByTenAndDiaChiAndTrangThaiPhanMem(tenDoanhNghiep, diaChi, trangThaiPhanMem, pageable);

        return Optional.ofNullable(doanhNghiepPage)
                .map(doanhNghieps -> doanhNghieps.getTotalElements() != 0 ? JsonResult.found(PageJson.build(doanhNghieps)) : JsonResult.notFound("DoanhNghiep/DiaChi/TrangThaiPhanMem"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

}
