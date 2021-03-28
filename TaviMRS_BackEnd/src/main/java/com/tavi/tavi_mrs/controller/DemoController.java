package com.tavi.tavi_mrs.controller;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.repository.lich_su_ket_noi.LichSuKetNoiRepo;
import com.tavi.tavi_mrs.repository.mo.MoRepo;
import com.tavi.tavi_mrs.repository.vi_pham.ViPhamRepo;
import com.tavi.tavi_mrs.repository.xe_van_chuyen.NhatKyXeVaoMoRepo;
import com.tavi.tavi_mrs.service.cms.CMSService;
import com.tavi.tavi_mrs.service.khac.FileService;
import com.tavi.tavi_mrs.service.vi_pham.ViPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class DemoController {

    //2020-02-14T01:00:00+00:00
    //2020-02-15T01:00:00.000Z

    @Autowired
    private CMSService cmsService;

    @Autowired
    private ViPhamRepo viPhamRepo;

    @Autowired
    private MoRepo moRepo;

    @Autowired
    private NhatKyXeVaoMoRepo nhatKyXeVaoMoRepo;

    @Autowired
    private LichSuKetNoiRepo lichSuKetNoiRepo;

    @Autowired
    private FileService fileService;

    @GetMapping("/convert-docx-pdf")
    public ResponseEntity<JsonResult> testConvert() throws Exception {
        //return JsonResult.success(viPhamRepo.findByMo(0));
        return JsonResult.badRequest("Forbidden");
    }

}
