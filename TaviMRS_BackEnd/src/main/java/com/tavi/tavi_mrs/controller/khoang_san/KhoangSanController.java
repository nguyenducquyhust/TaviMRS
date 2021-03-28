package com.tavi.tavi_mrs.controller.khoang_san;


import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import com.tavi.tavi_mrs.service.khoang_san.KhoangSanService;
import com.tavi.tavi_mrs.service.khoang_san.NhomKhoangSanService;
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
@RequestMapping("api/v1/admin/khoang-san")
public class KhoangSanController {

    @Autowired
    private KhoangSanService khoangSanService;

    @Autowired
    private NhomKhoangSanService nhomKhoangSanService;

    @GetMapping("find-by-id")
    ResponseEntity<JsonResult> search(@RequestParam("id") int idKhoangSan) {
        return khoangSanService.findByIdKhoangSanAndXoa(idKhoangSan, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/search")
    ResponseEntity<JsonResult> search(@RequestParam(value = "ten", defaultValue = "%", required = false) String ten,
                                      @RequestParam(value = "nhom-khoang-san-id", defaultValue = "0", required = false) int nhomKhoangSanId,
                                      @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<KhoangSan> khoangSanPage = khoangSanService.findByTenAndNhomKhoangSanId(ten, nhomKhoangSanId, pageable);
        if (khoangSanPage != null) {
            return JsonResult.found(PageJson.build(khoangSanPage));
        } else return JsonResult.parentNotFound("Ten/NhomKhoangSan/Page");
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll() {
        List<KhoangSan> khoangSanList = khoangSanService.findAll();

        return Optional.ofNullable(khoangSanList)
                .map(khoangSans -> !khoangSans.isEmpty() ? JsonResult.found(khoangSans) :
                        JsonResult.notFound("KhoangSan"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-all-to-page")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<KhoangSan> khoangSanPage = khoangSanService.findAllToPage(pageable);

        return Optional.ofNullable(khoangSanPage)
                .map(khoangSans -> khoangSans.getTotalElements() != 0 ? JsonResult.found(PageJson.build(khoangSans)) : JsonResult.notFound("Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-nhom-khoang-san")
    ResponseEntity<JsonResult> findByNhomKhoangSan(@RequestParam(name="nhom-khoang-san-id", defaultValue = "0",
            required = false) int nhomKhoangSanId ){

        List<KhoangSan> khoangSanList = khoangSanService.findByNhomKhoangSan(nhomKhoangSanId);

        return Optional.ofNullable(khoangSanList)
                .map(khoangSans -> !khoangSans.isEmpty() ? JsonResult.found(khoangSans) :
                        JsonResult.notFound("KhoangSan"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PostMapping("/upload")
    ResponseEntity<JsonResult> upload(@RequestParam("nhom-khoang-san-id") int nhomKhoangSanId,
                                      @RequestBody KhoangSan khoangSan) {
        return nhomKhoangSanService.findByIdNhomKhoangSanAndXoa(nhomKhoangSanId, false)
                .map(nhomKhoangSan -> {
                    khoangSan.setNhomKhoangSan(nhomKhoangSan);
                    return khoangSanService.save(khoangSan)
                            .map(JsonResult::uploaded)
                            .orElse(JsonResult.saveError("Save KhoangSan error"));
                })
                .orElse(JsonResult.parentNotFound("NhomKhoangSan "));
    }

    @PutMapping("/update")
    ResponseEntity<JsonResult> update(@Valid @RequestBody KhoangSan khoangSan) {
        return khoangSanService.save(khoangSan)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("Update KhoangSan error"));
    }

}
