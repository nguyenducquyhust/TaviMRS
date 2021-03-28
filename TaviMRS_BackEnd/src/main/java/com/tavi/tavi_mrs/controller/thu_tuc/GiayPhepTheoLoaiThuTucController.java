package com.tavi.tavi_mrs.controller.thu_tuc;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoLoaiThuTuc;
import com.tavi.tavi_mrs.service.thu_tuc.GiayPhepTheoLoaiThuTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/giay-phep-theo-loai-thu-tuc")
public class GiayPhepTheoLoaiThuTucController {

    @Autowired
    private GiayPhepTheoLoaiThuTucService giayPhepTheoLoaiThuTucService;

    @GetMapping("/find-by-loai-thu-tuc")
    public ResponseEntity<JsonResult> findByLoaiThuTuc(@RequestParam("loai-thu-tuc-id") int loaiThuTucId) {
        List<GiayPhepTheoLoaiThuTuc> giayPhepTheoLoaiThuTucList = giayPhepTheoLoaiThuTucService.findByLoaiThuTuc(loaiThuTucId);

        return Optional.ofNullable(giayPhepTheoLoaiThuTucList)
                .map(giayPhepTheoLoaiThuTucs -> ! giayPhepTheoLoaiThuTucs.isEmpty() ? JsonResult.found(giayPhepTheoLoaiThuTucs) :
                        JsonResult.notFound("GiayPhepTheoLoaiThuTuc"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}