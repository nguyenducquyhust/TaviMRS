package com.tavi.tavi_mrs.controller.thu_tuc;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.thu_tuc.ThuTuc;
import com.tavi.tavi_mrs.service.thu_tuc.ThuTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/thu-tuc")
public class ThuTucController {

    @Autowired
    private ThuTucService thuTucService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("thu-tuc-id") int thuTucId) {
        return thuTucService.findByIdThuTuc(thuTucId,false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-by-doanh-nghiep")
    public ResponseEntity<JsonResult> findByDoanhNghiep(@RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThuTuc> thuTucPage = thuTucService.findAllByDoanhNghiep(doanhNghiepId, pageable);

        return Optional.ofNullable(thuTucPage)
                .map(thuTucs -> thuTucs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(thuTucs)) : JsonResult.notFound("DoanhNghiep/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@Valid @RequestBody ThuTuc thuTuc) {
        return thuTucService.save(thuTuc)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("ThuTuc "));
    }

}
