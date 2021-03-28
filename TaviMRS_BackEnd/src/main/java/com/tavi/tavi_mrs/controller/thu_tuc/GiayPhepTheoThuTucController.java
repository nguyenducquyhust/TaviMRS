package com.tavi.tavi_mrs.controller.thu_tuc;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoThuTuc;
import com.tavi.tavi_mrs.service.thu_tuc.GiayPhepTheoThuTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/giay-phep-theo-thu-tuc")//upload
public class GiayPhepTheoThuTucController {

    @Autowired
    private GiayPhepTheoThuTucService giayPhepTheoThuTucService;

    @GetMapping("/find-by-thu-tuc")
    public ResponseEntity<JsonResult> findByThuTuc(@RequestParam("thu-tuc-id") int thuTucId) {
        List<GiayPhepTheoThuTuc> giayPhepTheoThuTucList = giayPhepTheoThuTucService.findByThuTuc(thuTucId);

        return Optional.ofNullable(giayPhepTheoThuTucList)
                .map(giayPhepTheoThuTucs -> ! giayPhepTheoThuTucs.isEmpty() ? JsonResult.found(giayPhepTheoThuTucs) :
                        JsonResult.notFound("GiayPhepTheoThuTuc"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}