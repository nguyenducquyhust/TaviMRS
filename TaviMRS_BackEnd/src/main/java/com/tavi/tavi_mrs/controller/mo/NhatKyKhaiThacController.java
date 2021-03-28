package com.tavi.tavi_mrs.controller.mo;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.mo.NhatKyKhaiThac;
import com.tavi.tavi_mrs.service.mo.NhatKyKhaiThacService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/nhat-ky-khai-thac")
public class NhatKyKhaiThacController {

    @Autowired
    private NhatKyKhaiThacService nhatKyKhaiThacService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("nhat-ky-khai-thac-id") int idNhatKyKhaiThac) {
        return nhatKyKhaiThacService.findByIdNhatKyKhaiThac(idNhatKyKhaiThac)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResult> findByMoAndNgay(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
                                                      @RequestParam(name = "ngay-dau",  defaultValue = "1970-01-01T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayDau,
                                                      @RequestParam(name = "ngay-cuoi", defaultValue = "9999-12-31T00:00:00+00:00", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayCuoi,
                                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhatKyKhaiThac> nkktpage = nhatKyKhaiThacService.findByMoAndNgay(moId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), pageable);

        return Optional.ofNullable(nkktpage)
                .map(nkkts -> nkkts.getTotalElements() != 0 ? JsonResult.found(PageJson.build(nkkts)) : JsonResult.notFound("NhatKyKhaiThac"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }
}
