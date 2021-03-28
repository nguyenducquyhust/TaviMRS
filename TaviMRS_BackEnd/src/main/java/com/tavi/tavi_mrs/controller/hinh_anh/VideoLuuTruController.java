package com.tavi.tavi_mrs.controller.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.VideoLuuTru;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.service.hinh_anh.VideoLuuTruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/video-luu-tru")
public class VideoLuuTruController {

    @Autowired
    private VideoLuuTruService videoLuuTruService;

    @GetMapping("/find-by-vi-pham")
    public ResponseEntity<JsonResult> findByMo(@RequestParam("vi-pham-id") int viPhamId) {
        return Optional.ofNullable(videoLuuTruService.findByViPham(viPhamId))
                .map(videoLuuTrus -> videoLuuTrus.isEmpty() ? JsonResult.notFound("vi pham") : JsonResult.found(videoLuuTrus))
                .orElse(JsonResult.serverError("server error"));
    }
}
