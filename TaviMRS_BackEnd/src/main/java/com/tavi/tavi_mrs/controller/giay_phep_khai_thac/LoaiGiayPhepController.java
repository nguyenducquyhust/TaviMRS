package com.tavi.tavi_mrs.controller.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.LoaiGiayPhep;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.service.giay_phep_khai_thac.LoaiGiayPhepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/loai-giay-phep")
public class LoaiGiayPhepController {

    @Autowired
    private LoaiGiayPhepService loaiGiayPhepService;

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll() {
        List<LoaiGiayPhep> loaiGiayPhepList = loaiGiayPhepService.findAllByXoa(false);

        return Optional.ofNullable(loaiGiayPhepList)
                .map(loaiGiayPheps -> !loaiGiayPheps.isEmpty() ? JsonResult.found(loaiGiayPheps) :
                        JsonResult.notFound("LoaiGiayPhep"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-all-tcgp")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LoaiGiayPhep> loaiGiayPhepPage = loaiGiayPhepService.findAll(pageable);

        return Optional.ofNullable(loaiGiayPhepPage)
                .map(loaiGiayPheps -> loaiGiayPheps.getTotalElements() != 0 ? JsonResult.found(PageJson.build(loaiGiayPheps)) : JsonResult.notFound("Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}
