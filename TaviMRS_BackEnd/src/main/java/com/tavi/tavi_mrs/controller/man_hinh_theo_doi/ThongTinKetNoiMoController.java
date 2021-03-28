package com.tavi.tavi_mrs.controller.man_hinh_theo_doi;

import com.tavi.tavi_mrs.entities.common.TrangThaiKetNoi;
import com.tavi.tavi_mrs.entities.common.TrangThaiLichSuKetNoi;
import com.tavi.tavi_mrs.entities.common.TrangThaiThietBi;
import com.tavi.tavi_mrs.entities.common.TrangThaiXuLy;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoiThietBi;
import com.tavi.tavi_mrs.entities.man_hinh_theo_doi.ThietBiMatKetNoi;
import com.tavi.tavi_mrs.entities.man_hinh_theo_doi.ThongTinKetNoiMo;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;
import com.tavi.tavi_mrs.service.lich_su_ket_noi.LichSuKetNoiService;
import com.tavi.tavi_mrs.service.lich_su_ket_noi.LichSuKetNoiThietBiService;
import com.tavi.tavi_mrs.service.man_hinh_theo_doi.ThongTinKetNoiMoService;
import com.tavi.tavi_mrs.service.mo.MoService;
import com.tavi.tavi_mrs.service.mo.NhatKyKhaiThacService;
import com.tavi.tavi_mrs.service.thiet_bi.ThietBiService;
import com.tavi.tavi_mrs.service.vi_pham.ViPhamService;
import com.tavi.tavi_mrs.service.xe_van_chuyen.NhatKyXeVaoMoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/admin/thong-tin-ket-noi-mo")
public class ThongTinKetNoiMoController {

    @Autowired
    private ThongTinKetNoiMoService thongTinKetNoiMoService;

    @Autowired
    private LichSuKetNoiService lichSuKetNoiService;

    @Autowired
    private LichSuKetNoiThietBiService lichSuKetNoiThietBiService;

    @Autowired
    private NhatKyXeVaoMoService nhatKyXeVaoMoService;

    @Autowired
    private ViPhamService viPhamService;

    @Autowired
    private ThietBiService thietBiService;

    @Autowired
    private MoService moService;

    @GetMapping("/ping")
    ResponseEntity<JsonResult> ping(@RequestParam("mo-id") Integer moId,
                                    @RequestParam("url-mo") String urlMo) {
        return moService.findByIdMoAndXoa(moId, false)
                .map(mo -> {
                    return Optional.ofNullable(thongTinKetNoiMoService.pingMo(urlMo))
                            .map(thietBiMatKetNois -> {

                                // update Thoi gian ket noi lai
                                lichSuKetNoiService.updateThoiGianKetNoiLaiByMo(moId, new Date());

                                // update Trang thai cua Mo
                                mo.setTrangThaiKetNoi(true);
                                moService.updateTrangThaiKetNoiByIdMo(moId, TrangThaiKetNoi.KET_NOI);

                                // reset Trang thai cua Thiet Bi theo mo
                                thietBiService.updateTrangThaiKetNoiByMo(moId, TrangThaiThietBi.KET_NOI);

                                if (!thietBiMatKetNois.isEmpty()) {
                                    // add Lich Su Ket Noi
                                    LichSuKetNoi lskn = LichSuKetNoi.builder().mo(mo).trangThai(TrangThaiLichSuKetNoi.KET_NOI_NHUNG_MAT_KET_NOI_THIET_BI).xoa(false).build();
                                    lichSuKetNoiService.save(lskn).ifPresent(saved -> {
                                        // update Trang thai ket noi cua Thiet Bi va add Lich su ket noi thiet bi
                                        thietBiMatKetNois.forEach(t -> {
                                            ThietBi tb = thietBiService.findByIdThietBiAndXoa(t.getIdThietBi(), false).get();
                                            tb.setTrangThai(TrangThaiThietBi.KHONG_KET_NOI);
                                            thietBiService.save(tb);
                                            lichSuKetNoiThietBiService.save(LichSuKetNoiThietBi.builder().thietBi(tb).moTa(t.getMoTa()).xoa(false).build());
                                        });
                                    });
                                }

                                // collect Loai vi pham
                                Set<LoaiViPham> loaiViPhams = viPhamService.findLoaiViPhamByMoAndTrangThaiXuLy(moId, TrangThaiXuLy.CHUA_XU_LY);

                                // collect Luong xe ra vao mo
                                Integer luongXe = nhatKyXeVaoMoService.countByMoAndNgay(moId, new Date());

                                // summarize Info
                                ThongTinKetNoiMo rs = new ThongTinKetNoiMo(mo, TrangThaiKetNoi.KET_NOI, thietBiMatKetNois.size(), loaiViPhams, luongXe);

                                return JsonResult.found(rs);
                            })
                            .orElse(JsonResult.serverError("Internal Server Error"));
                })
                .orElse(JsonResult.notFound("Mo"));
    }

}
