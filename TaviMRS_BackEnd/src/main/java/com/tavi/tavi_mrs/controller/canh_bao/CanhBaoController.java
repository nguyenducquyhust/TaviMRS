package com.tavi.tavi_mrs.controller.canh_bao;


import com.tavi.tavi_mrs.entities.bao_cao.BaoCao;
import com.tavi.tavi_mrs.entities.common.TrangThaiXuLy;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.service.bao_cao.BaoCaoService;
import com.tavi.tavi_mrs.service.canh_bao.CanhBaoService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/canh-bao")
public class CanhBaoController {

    @Autowired
    private CanhBaoService canhBaoService;

    @GetMapping("/find")
    public ResponseEntity<JsonResult> getBacklogWarnings() {
        return Optional.ofNullable(canhBaoService.findCanhBaoManHinhTheoDoi())
                .map(canhBaos -> !canhBaos.isEmpty() ? JsonResult.found(canhBaos) : JsonResult.notFound("Canh Bao"))
                .orElse(JsonResult.serverError("Internal Server Error"));

    }
}
