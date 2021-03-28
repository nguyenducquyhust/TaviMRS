package com.tavi.tavi_mrs.controller.lich_su_ket_noi;


import com.tavi.tavi_mrs.entities.common.TrangThaiLichSuKetNoi;
import com.tavi.tavi_mrs.entities.common.TrangThaiXuLy;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.service.lich_su_ket_noi.LichSuKetNoiService;
import com.tavi.tavi_mrs.service.mo.MoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/lich-su-ket-noi")
public class LichSuKetNoiController {

    @Autowired
    private LichSuKetNoiService lichSuKetNoiService;

    @Autowired
    private MoService moService;

    @GetMapping("/find-by-trang-thai-xu-ly")
    public ResponseEntity<JsonResult> findByTrangThaiXuLy(@RequestParam("trang-thai-xu-ly") int trangThaiXuLy) {
        return Optional.ofNullable(lichSuKetNoiService.findByTrangThaiXuLy(trangThaiXuLy))
                .map(lichSuKetNois -> !lichSuKetNois.isEmpty() ? JsonResult.found(lichSuKetNois) : JsonResult.notFound("TrangThaiKetNoi"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByTrangThaiXuLy(@RequestParam("mo-id") Integer moId,
                                                          @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                          @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return Optional.ofNullable(lichSuKetNoiService.findByMo(moId, pageable))
                .map(resultPage -> resultPage.getTotalElements() != 0 ? JsonResult.found(PageJson.build(resultPage)) : JsonResult.notFound("Mo/Page"))
                .orElse(JsonResult.serverError("Internal Server Error"));
    }

    @PostMapping("/upload")
    public ResponseEntity<JsonResult> upload(@RequestParam("mo-id") Integer moId,
                                             @RequestBody LichSuKetNoi lichSuKetNoi) {
        return moService.findByIdMoAndXoa(moId, false)
                .map(mo -> {
                    lichSuKetNoi.setMo(mo);
                    lichSuKetNoi.setTrangThai(TrangThaiLichSuKetNoi.MAT_KET_NOI_CHUA_PHUC_HOI);
                    lichSuKetNoi.setTrangThaiXuLy(TrangThaiXuLy.CHUA_XU_LY);
                    return lichSuKetNoiService.save(lichSuKetNoi)
                            .map(saved -> saved.getIdLichSuKetNoi() != null ? JsonResult.uploaded(saved) : JsonResult.saveError("Mỏ" + mo.getIdMo() + " liên tục mất kết nối!"))
                            .orElse(JsonResult.saveError("Internal Server Error"));
                })
                .orElse(JsonResult.parentNotFound("Mo"));

    }
}
