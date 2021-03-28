package com.tavi.tavi_mrs.controller.thiet_bi;


import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import com.tavi.tavi_mrs.service.mo.MoService;
import com.tavi.tavi_mrs.service.thiet_bi.ThietBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/thiet-bi")
public class ThietBiController {

    @Autowired
    private ThietBiService thietBiService;
    @Autowired
    private MoService moService;
    @Autowired
    private DoanhNghiepService doanhNghiepService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("id") int idThietBi) {
        return thietBiService.findByIdThietBiAndXoa(idThietBi, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }

    @GetMapping("/find-all")
    ResponseEntity<JsonResult> findAllToPage(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThietBi> thietBis = thietBiService.findAll(pageable);
        return Optional.ofNullable(thietBis)
                .map(has -> has.getTotalElements() != 0 ? JsonResult.found(PageJson.build(has)) : JsonResult.notFound("ThietBi/Page"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByMo(@RequestParam("mo-id") int moId,
                                               @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThietBi> thietBis = thietBiService.findByMoId(moId, pageable);
        if (thietBis != null) {
            return JsonResult.found(PageJson.build(thietBis));
        }
        return JsonResult.parentNotFound("Mo");
    }

    @GetMapping("/find-by-doanh-nghiep")
    public ResponseEntity<JsonResult> findByDoanhNghiep(@RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                                        @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                        @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThietBi> thietBis = thietBiService.findByDoanhNghiepId(doanhNghiepId, pageable);
        return Optional.ofNullable(thietBis)
                .map(thietBi -> thietBi.getTotalElements() != 0 ? JsonResult.found(PageJson.build(thietBi)) : JsonResult.notFound("ThietBi/DoanhNghiep"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

//    @GetMapping("/find-by-mo-and-trang-thai-and-ten")
//    public ResponseEntity<JsonResult> search(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
//                                             @RequestParam(name = "trang-thai", defaultValue = "0", required = false) int trangThai,
//                                             @RequestParam(name = "ten", defaultValue = "") String tenThietBi,
//                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
//                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<ThietBi> thietBis = thietBiService.findByMoAndTrangThaiAndTen(moId, trangThai, tenThietBi, pageable);
//        if (thietBis != null) {
//            return JsonResult.found(PageJson.build(thietBis));
//        }
//        return JsonResult.parentNotFound("FieldToSearch");
//    }

//    @GetMapping("/search")
//    public ResponseEntity<JsonResult> search(@RequestParam(name = "mo-id", defaultValue = "0", required = false) int moId,
//                                             @RequestParam(name = "doanh-nghiep-id", defaultValue = "0", required = false) int doanhNghiepId,
//                                             @RequestParam(name = "trang-thai", defaultValue = "0", required = false) int trangThai,
//                                             @RequestParam(name = "trang-thai-ket-noi", required = false) Boolean trangThaiKetNoi,
//                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
//                                             @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<ThietBi> thietBis = thietBiService.findByMoIdAndDoanhNghiepIdAndTrangThaiKetNoiAndTrangThai(moId, doanhNghiepId, trangThaiKetNoi, trangThai, pageable);
//        if (thietBis != null) {
//            return JsonResult.found(PageJson.build(thietBis));
//        }
//        return JsonResult.parentNotFound("FieldToSearch");
//    }

    @GetMapping("/search")
    ResponseEntity<JsonResult> search(@RequestParam(value = "id-mo", required = false, defaultValue = "0") int idMo,
                                      @RequestParam(value = "trang-thai-ket-noi", required = false, defaultValue = "-1") int trangThaiKetNoi,
                                      @RequestParam(value = "ma-thiet-bi", defaultValue = "", required = false) String maThietBi,
                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        if (idMo == 0 && trangThaiKetNoi == 0 && maThietBi == "") {
            Page<ThietBi> thietBiPage = thietBiService.findAll(pageable);
        }
        Page<ThietBi> thietBiPage = thietBiService.findByMoAndTrangThaiAndMaThieBi(idMo, trangThaiKetNoi, maThietBi, pageable);

        return Optional.ofNullable(thietBiPage)
                .map(thietBis -> thietBis.getTotalElements() != 0 ? JsonResult.found(PageJson.build(thietBis)) : JsonResult.notFound("ThietBi/Mo/TrangThai/MaThietBi"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestParam("mo-id") int moId,
                                             @RequestParam("doanh-nghiep-id") int doanhNghiepId,
                                             @RequestBody ThietBi thietBi) {

        return doanhNghiepService.findByIdDoanhNghiepAndXoa(doanhNghiepId, false)
                .map(doanhNghiep -> {
                    thietBi.setDoanhNghiep(doanhNghiep);
                    return moService.findByIdMoAndXoa(moId, false)
                            .map(mo -> {
                                thietBi.setMo(mo);
                                return thietBiService.save(thietBi)
                                        .map(JsonResult::uploaded)
                                        .orElse(JsonResult.saveError("Upload Thiet Bi Error"));
                            })
                            .orElse(JsonResult.parentNotFound("Mo "));
                })
                .orElse(JsonResult.parentNotFound("DoanhNghiep "));

    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@Valid @RequestBody ThietBi thietBi) {
        return thietBiService.save(thietBi)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("Update ThietBi error"));

    }
}
