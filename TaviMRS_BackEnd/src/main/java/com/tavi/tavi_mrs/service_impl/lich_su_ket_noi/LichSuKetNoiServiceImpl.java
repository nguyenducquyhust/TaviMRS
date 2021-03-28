package com.tavi.tavi_mrs.service_impl.lich_su_ket_noi;

import com.tavi.tavi_mrs.entities.common.TrangThaiLichSuKetNoi;
import com.tavi.tavi_mrs.entities.common.TrangThaiXuLy;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.repository.lich_su_ket_noi.LichSuKetNoiRepo;
import com.tavi.tavi_mrs.service.lich_su_ket_noi.LichSuKetNoiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LichSuKetNoiServiceImpl implements LichSuKetNoiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LichSuKetNoiServiceImpl.class);

    @Autowired
    private LichSuKetNoiRepo lichSuKetNoiRepo;

    @Override
    public Optional<LichSuKetNoi> findByIdLichSuKetNoiAndXoa(int idLichSuKetNoi, boolean xoa) {
        try {
            return lichSuKetNoiRepo.findByIdLichSuKetNoiAndXoa(idLichSuKetNoi, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdLichSuKetNoiAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<LichSuKetNoi> findByTrangThaiXuLy(int trangThaiXuLy) {
        try {
            return lichSuKetNoiRepo.findByTrangThaiXuLy(trangThaiXuLy);
        } catch (Exception ex) {
            LOGGER.error("findByTrangThaiXuLy error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<LichSuKetNoi> findByMo(Integer moId, Pageable pageable) {
        try {
            return lichSuKetNoiRepo.findByMo(moId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMo error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<LichSuKetNoi> save(LichSuKetNoi lskn) {
        try {
            if (lichSuKetNoiRepo.existDangXuLyOrChuaXuLy(lskn.getMo().getIdMo()) > 0) {
                return Optional.ofNullable(lichSuKetNoiRepo.save(lskn));
            } else return Optional.ofNullable(lskn);
        } catch (Exception ex) {
            LOGGER.error("saveLichSuKetNoi error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<LichSuKetNoi> update(LichSuKetNoi lichSuKetNoi) {
        try {
            return Optional.ofNullable(lichSuKetNoiRepo.save(lichSuKetNoi));
        } catch (Exception ex) {
            LOGGER.error("updateLichSuKetNoi error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean updateThoiGianKetNoiLaiByMo(Integer moId, Date thoiGianKetNoiLai) {
        try {
            return lichSuKetNoiRepo.updateThoiGianKetNoiLaiByMoAndTrangThai(moId, TrangThaiLichSuKetNoi.MAT_KET_NOI_CHUA_PHUC_HOI, TrangThaiLichSuKetNoi.MAT_KET_NOI_DA_PHUC_HOI, thoiGianKetNoiLai, TrangThaiXuLy.DA_XU_LY) > 0;
        } catch (Exception ex) {
            LOGGER.error("updateThoiGianKetNoiLaiByMo error", ex);
            ex.printStackTrace();
            return false;
        }
    }
}
