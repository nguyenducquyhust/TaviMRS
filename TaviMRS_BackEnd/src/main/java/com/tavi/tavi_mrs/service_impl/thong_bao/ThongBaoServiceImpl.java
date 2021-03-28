package com.tavi.tavi_mrs.service_impl.thong_bao;

import com.tavi.tavi_mrs.entities.common.HinhThucGui;
import com.tavi.tavi_mrs.entities.json.AnnounceForm;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import com.tavi.tavi_mrs.entities.thong_bao.TaiLieuDinhKem;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
import com.tavi.tavi_mrs.repository.nguoi_dung.NguoiDungRepo;
import com.tavi.tavi_mrs.repository.thong_bao.TaiLieuDinhKemRepo;
import com.tavi.tavi_mrs.repository.thong_bao.ThongBaoRepo;
import com.tavi.tavi_mrs.service.khac.MailService;
import com.tavi.tavi_mrs.service.thong_bao.ThongBaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class ThongBaoServiceImpl implements ThongBaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThongBaoServiceImpl.class);

    @Autowired
    private ThongBaoRepo thongBaoRepo;

    @Autowired
    private NguoiDungRepo nguoiDungRepo;

    @Autowired
    private TaiLieuDinhKemRepo taiLieuDinhKemRepo;

    @Autowired
    private MailService mailService;

    @Value("${spring.upload.link-prefix}")
    String linkPrefix;

    @Override
    public Optional<ThongBao> findByIdThongBaoAndXoa(int idThongBao, boolean xoa) {
        try {
            return thongBaoRepo.findByIdThongBaoAndXoa(idThongBao, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdThongBaoAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<ThongBao> save(ThongBao thongBao) {
        try {
            return Optional.ofNullable(thongBaoRepo.save(thongBao));
        } catch (Exception ex) {
            LOGGER.error("saveThongBao error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int idThongBao) {
        try {
            return thongBaoRepo.deleteByIdThongBao(idThongBao) > 0;
        } catch (Exception ex) {
            LOGGER.error("deleteByIdThongBao error", ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<ThongBao> findAll(Pageable pageable) {
        try {
            return thongBaoRepo.findAll(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllByXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ThongBao> findByThoiGianGuiAndTieuDe(Date ngayDau, Date ngayCuoi, String tieuDe, Pageable pageable) {
        try {
            return thongBaoRepo.findByThoiGianGuiAndTieuDe(ngayDau, ngayCuoi, tieuDe, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByThoiGianGuiAndTieuDeAndXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    public boolean sendAnnouncementViaEmail(String tieuDe, String noiDung, List<String> emailList, List<String> fileDinhKemList) throws MessagingException {
        try {
            mailService.sendMailWithAttachment(Arrays.copyOf(emailList.toArray(), emailList.size(), String[].class), tieuDe, noiDung, fileDinhKemList);
            return true;
        } catch (Exception ex) {
            LOGGER.error("sendAnnounmentViaEmail error", ex);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean sendAnnouncementViaApp(String tieuDe, String noiDung, String fileDinhKem, List<String> emailList) {
        try {
            return true;
        } catch (Exception ex) {
            LOGGER.error("sendAnnounmentViaApp error", ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ResponseEntity<JsonResult> sendAnnouncement(AnnounceForm f) throws MessagingException {
        try {
            ThongBao tb = new ThongBao();
            tb.setTieuDe(f.getTieuDe());
            tb.setNoiDung(f.getNoiDung());
            // Khong set hinh thuc gui de phong bug giua chung
            //tb.setHinhThucGui(f.getHinhThucGui());
            tb.setThoiGianGui(new Date());
            tb.setYeuCauPhanHoi(f.getYeuCauPhanHoi());
            tb.setSoLuongDonViNhan(f.getSoLuongDonViNhan());
            List<NguoiDung> listNguoiDung = nguoiDungRepo.findByIdList(f.getListId());
            tb.setThongBaoCoNguoiNhan(listNguoiDung);
            tb.setXoa(false);
            List<String> listEmail = new ArrayList<>();
            listNguoiDung.forEach(l -> listEmail.add(l.getEmail()));
            // gui thong bao
            switch (f.getHinhThucGui()) {
                case HinhThucGui.QUA_EMAIL: {
                    boolean sentViaMail = sendAnnouncementViaEmail(f.getTieuDe(), f.getNoiDung(), listEmail, f.getFileDinhKemList());
                    if (!sentViaMail) return JsonResult.serverError("Khong the gui qua mail");
                    break;
                }
                case HinhThucGui.QUA_PHAN_MEM: {
                    boolean sentViaApp = sendAnnouncementViaApp(f.getTieuDe(), f.getNoiDung(), null, listEmail);
                    if (!sentViaApp) return JsonResult.serverError("Khong the gui qua phan mem");
                    break;
                }
                case HinhThucGui.QUA_EMAIL_VA_PHAN_MEM: {
                    boolean sentViaMail = sendAnnouncementViaEmail(f.getTieuDe(), f.getNoiDung(), listEmail, f.getFileDinhKemList());
                    if (!sentViaMail) return JsonResult.serverError("Khong the gui qua mail");
                    boolean sentViaApp = sendAnnouncementViaApp(f.getTieuDe(), f.getNoiDung(), null, listEmail);
                    if (!sentViaApp) {
                        // save thong bao da gui qua mail
                        tb.setHinhThucGui(HinhThucGui.QUA_EMAIL);
                        ThongBao tmp = thongBaoRepo.save(tb);
                        if (tmp != null) {
                            List<String> listFile = f.getFileDinhKemList();
                            for(String t: listFile) {
                                TaiLieuDinhKem tl = new TaiLieuDinhKem();
                                tl.setThongBao(tb);
                                tl.setDuongDan(t);
                                taiLieuDinhKemRepo.save(tl);
                            }
                        }
                        else return JsonResult.serverError("Khong the gui thong bao");
                        return JsonResult.serverError("Da gui qua mail va Khong the gui qua phan mem");
                    }
                    break;
                }
            }
            // save thong bao
            tb.setHinhThucGui(f.getHinhThucGui());
            return Optional.ofNullable(thongBaoRepo.save(tb))
                    .map(thongBao -> {
                        // cap nhat tai lieu dinh kem
                        List<String> listFile = f.getFileDinhKemList();
                        for(String t: listFile) {
                            TaiLieuDinhKem tl = new TaiLieuDinhKem();
                            tl.setThongBao(thongBao);
                            tl.setDuongDan(t);
                            if (taiLieuDinhKemRepo.save(tl) == null) JsonResult.saveError("Đính kèm tệp không thành công") ;
                        }
                        return JsonResult.success(thongBao);
                    })
                    .orElse(JsonResult.saveError("Da gui nhung khong the luu thong bao vao he thong"));
        } catch (Exception ex) {
            LOGGER.error("sendAnnounment error", ex);
            ex.printStackTrace();
            return JsonResult.serverError("Khong the gui thong bao");
        }
    }
}
