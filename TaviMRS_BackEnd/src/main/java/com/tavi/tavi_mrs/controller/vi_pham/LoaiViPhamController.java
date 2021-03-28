package com.tavi.tavi_mrs.controller.vi_pham;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;
import com.tavi.tavi_mrs.service.vi_pham.LoaiViPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/loai-vi-pham")
public class LoaiViPhamController {

    @Autowired
    private LoaiViPhamService loaiViPhamService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("vi-pham-id") int idLoaiViPham) {
        return loaiViPhamService.findByIdLoaiViPhamAndXoa(idLoaiViPham, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll() {
        List<LoaiViPham> loaiViPhamList = loaiViPhamService.findAllByXoa(false);
        return Optional.ofNullable(loaiViPhamList)
                .map(loaiViPhams -> ! loaiViPhams.isEmpty() ? JsonResult.found(loaiViPhams) :
                        JsonResult.notFound("LoaiViPham"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestBody LoaiViPham loaiViPham) {
        return loaiViPhamService.save(loaiViPham)
                .map(JsonResult::uploaded)
                .orElse(JsonResult.saveError("LoaiViPham "));
    }

    @PostMapping("/update")
    public ResponseEntity<JsonResult> update(@Valid @RequestBody LoaiViPham loaiViPham) {
        return loaiViPhamService.save(loaiViPham)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError( "LoaiViPham "));
    }
}
