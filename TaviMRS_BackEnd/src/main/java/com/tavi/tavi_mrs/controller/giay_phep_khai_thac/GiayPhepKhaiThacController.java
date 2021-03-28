package com.tavi.tavi_mrs.controller.giay_phep_khai_thac;


import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import com.tavi.tavi_mrs.service.giay_phep_khai_thac.GiayPhepKhaiThacService;
import com.tavi.tavi_mrs.service.giay_phep_khai_thac.LoaiGiayPhepService;
import com.tavi.tavi_mrs.service.mo.MoService;
import com.tavi.tavi_mrs.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin/giay-phep-khai-thac")
public class GiayPhepKhaiThacController {

    @Autowired
    private GiayPhepKhaiThacService giayPhepKhaiThacService;

    @Autowired
    private LoaiGiayPhepService loaiGiayPhepService;

    @Autowired
    private MoService moService;

    @Autowired
    private DoanhNghiepService doanhNghiepService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("giay-phep-id") int giayPhepId) {
        return giayPhepKhaiThacService.findByIdGiayPhepAndXoa(giayPhepId, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByMo(@RequestParam("mo-id") int moId,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<GiayPhepKhaiThac> giayPhepKhaiThacPage = giayPhepKhaiThacService.findByMoAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(moId, 0, 0, 0, pageable);
        return Optional.ofNullable(giayPhepKhaiThacPage)
                .map(giayPhepKhaiThacs -> giayPhepKhaiThacs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(giayPhepKhaiThacs)) : JsonResult.notFound("Mo/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-doanh-nghiep")
    public ResponseEntity<JsonResult> findByDoanhNghiep(@RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<GiayPhepKhaiThac> giayPhepKhaiThacPage = giayPhepKhaiThacService.findByDoanhNghiepAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(doanhNghiepId, 0, 0, 0, pageable);
        return Optional.ofNullable(giayPhepKhaiThacPage)
                .map(giayPhepKhaiThacs -> giayPhepKhaiThacs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(giayPhepKhaiThacs)) : JsonResult.notFound("DoanhNghiep/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestParam("loai-giay-phep-id") int loaiGiayPhepId,
                                             @RequestParam("mo-id") int moId,
                                             @RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                             @RequestBody GiayPhepKhaiThac giayPhepKhaiThac) {
        return loaiGiayPhepService.findByIdLoaiGiayPhepAndXoa(loaiGiayPhepId, false)
                .map(loaiGiayPhep -> {
                    giayPhepKhaiThac.setLoaiGiayPhep(loaiGiayPhep);
                    return doanhNghiepService.findByIdDoanhNghiepAndXoa(doanhNghiepId, false)
                            .map(doanhNghiep -> {
                                giayPhepKhaiThac.setDoanhNghiep(doanhNghiep);
                                return moService.findByIdMoAndXoa(moId,false)
                                        .map(mo -> {
                                            giayPhepKhaiThac.setMo(mo);
                                            return giayPhepKhaiThacService.save(giayPhepKhaiThac)
                                                    .map(JsonResult::uploaded)
                                                    .orElse(JsonResult.saveError("GiayPhepKhaiThac "));
                                        })
                                        .orElse(JsonResult.saveError("Mo "));
                            })
                            .orElse(JsonResult.parentNotFound("DoanhNghiep "));
                })
                .orElse(JsonResult.parentNotFound("LoaiGiayPhep "));
    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@Valid @RequestBody GiayPhepKhaiThac giayPhepKhaiThac) {
        return giayPhepKhaiThacService.save(giayPhepKhaiThac)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("GiayPhepKhaiThac "));
    }

    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<GiayPhepKhaiThac> giayPhepKhaiThacPage = giayPhepKhaiThacService.findAll(pageable);

        return Optional.ofNullable(giayPhepKhaiThacPage)
                .map(giayPhepKhaiThacs -> giayPhepKhaiThacs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(giayPhepKhaiThacs)) : JsonResult.notFound("Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/search")
    ResponseEntity<JsonResult> search(@RequestParam(value = "id-mo", required = false, defaultValue = "0") int idMo,
                                      @RequestParam(value = "loai-giay-phep-id", required = false, defaultValue = "0") int loaiGiayPhepId,
                                      @RequestParam(value = "trang-thai", defaultValue = "-1", required = false) int trangThai,
                                      @RequestParam(value = "tham-quyen-cap", defaultValue = "-1", required = false) int thamQuyenCap,
                                      @RequestParam(value = "so-quyet-dinh", defaultValue = "", required = false) String soQuyetDinh,
                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        if (idMo == 0  && loaiGiayPhepId == 0 && trangThai == -1 && thamQuyenCap == -1 && soQuyetDinh == "" ) {
            Page<GiayPhepKhaiThac> giayPhepKhaiThacPage = giayPhepKhaiThacService.findAll(pageable);
        }
        Page<GiayPhepKhaiThac> giayPhepKhaiThacPage = giayPhepKhaiThacService.findByIdMoAndLoaiGiayPhepIdAndTrangThaiAndThamQuyenCapAndSoQuyetDinh(idMo, loaiGiayPhepId, trangThai, thamQuyenCap, soQuyetDinh, pageable);

        return Optional.ofNullable(giayPhepKhaiThacPage)
                .map(giayPhepKhaiThacs -> giayPhepKhaiThacs.getTotalElements() != 0 ? JsonResult.found(PageJson.build(giayPhepKhaiThacs)) : JsonResult.notFound("IdMo/LoaiGiayPhepIdTrangThai/ThamQuyenCap/SoQuyetDinh"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

}
