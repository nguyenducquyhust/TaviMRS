package com.tavi.tavi_mrs.controller.khoang_san;


import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import com.tavi.tavi_mrs.entities.khoang_san.NhomKhoangSan;
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
@RequestMapping("api/v1/admin/nhom-khoang-san")
public class NhomKhoangSanController {

    @Autowired
    private NhomKhoangSanService nhomKhoangSanService;

    @GetMapping("find-by-id")
    ResponseEntity<JsonResult> search(@RequestParam("id") int idNhomKhoangSan) {
        return nhomKhoangSanService.findByIdNhomKhoangSanAndXoa(idNhomKhoangSan, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/search")
    ResponseEntity<JsonResult> search(@RequestParam(value = "ten", defaultValue = "%", required = false) String ten,
                                      @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return Optional.ofNullable(nhomKhoangSanService.findByTen(ten, pageable))
                .map(rsPage -> rsPage.getTotalElements() != 0 ? JsonResult.found(PageJson.build(rsPage)) : JsonResult.notFound("NhomKhoangSan"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PostMapping("/upload")
    ResponseEntity<JsonResult> upload(@RequestBody NhomKhoangSan nhomKhoangSan) {
        return nhomKhoangSanService.save(nhomKhoangSan)
                .map(JsonResult::uploaded)
                .orElse(JsonResult.saveError("Upload NhomKhoangSan error"));
    }

    @PutMapping("/update")
    ResponseEntity<JsonResult> update(@Valid @RequestBody NhomKhoangSan nhomKhoangSan) {
        return nhomKhoangSanService.save(nhomKhoangSan)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("Update NhomKhoangSan error"));
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll() {
        List<NhomKhoangSan> nhomKhoangSanList = nhomKhoangSanService.findAllByXoa(false);

        return Optional.ofNullable(nhomKhoangSanList)
                .map(nhomKhoangSans -> !nhomKhoangSans.isEmpty() ? JsonResult.found(nhomKhoangSans) :
                        JsonResult.notFound("NhomKhoangSan"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

}
