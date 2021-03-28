package com.tavi.tavi_mrs.controller.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.service.xe_van_chuyen.NhatKyXeVaoMoService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/nhat-ky-xe-vao-mo")
public class NhatKyXeVaoMoController {
    @Autowired
    private NhatKyXeVaoMoService nhatKyXeVaoMoService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("nhat-ki-xe-vao-mo-id") int idNhatKyXeVaoMo) {

        return nhatKyXeVaoMoService.findByIdNhatKyXeVaoMo(idNhatKyXeVaoMo)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-by-mo-and-thoi-gian")
    public ResponseEntity<JsonResult> findByMoIdAndThoiGian(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
                                                            @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                            @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi,
                                                            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        return Optional.ofNullable(nhatKyXeVaoMoService.findByMoAndThoiGian(moId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi), pageable))
                .map(rsPage -> rsPage.getTotalElements() != 0 ? JsonResult.found(PageJson.build(rsPage)) : JsonResult.notFound("Mo"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-mo-and-xe-and-thoi-gian")
    public ResponseEntity<JsonResult> findByMoIdAndThoiGian(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
                                                            @RequestParam(name = "xe-id", defaultValue = "0", required = false) int xeId,
                                                            @RequestParam(name = "ngay-dau", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayDau,
                                                            @RequestParam(name = "ngay-cuoi", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayCuoi) {

        return Optional.ofNullable(nhatKyXeVaoMoService.findByMoAndIdXeAndThoiGian(moId, xeId, DateTimeUtils.asDate(ngayDau), DateTimeUtils.asDate(ngayCuoi)))
                .map(resultList -> !resultList.isEmpty() ? JsonResult.found(resultList) : JsonResult.notFound("Mo/Xe"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-tong-khai-thac")
    public ResponseEntity<JsonResult> findTongKhaiThac(@RequestParam("xe-id") int xeVanChuyenId,
                                                       @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                       @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

//        Pageable pageable = PageRequest.of(page - 1, size);
//        List<Tuple> nhatKyXeVaoMos = nhatKyXeVaoMoService.findTongKhaiThac(xeVanChuyenId);
//        if (nhatKyXeVaoMos != null) {
//            return JsonResult.found(PageJson.build((Page<?>) nhatKyXeVaoMos));
//        }
        return JsonResult.parentNotFound("NhatKyXeVaoMo");
    }

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByMo(@RequestParam("mo-id") int idMo,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        return Optional.ofNullable(nhatKyXeVaoMoService.findByMo(idMo, pageable))
                .map(resultList -> !resultList.isEmpty() ? JsonResult.found(resultList) : JsonResult.notFound("DoanhNghiep"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }
}
