package com.tavi.tavi_mrs.service_impl.thiet_bi;

import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.repository.thiet_bi.ThietBiRepo;
import com.tavi.tavi_mrs.service.thiet_bi.ThietBiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThietBiServiceImpl implements ThietBiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThietBiServiceImpl.class);

    @Autowired
    private ThietBiRepo thietBiRepo;

    @Override
    public Optional<ThietBi> findByIdThietBiAndXoa(int idThietBi, boolean xoa) {
        try {
            return thietBiRepo.findByIdThietBiAndXoa(idThietBi, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdThietBiAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<ThietBi> findAll(Pageable pageable) {
        try {
            return thietBiRepo.findAll(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAll error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ThietBi> findByMoAndTrangThaiAndTen(int idMo, int trangThai, String tenThietBi, Pageable pageable) {
        try {
            return thietBiRepo.findByMoAndTrangThaiAndTen(idMo, trangThai, tenThietBi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoAndTrangThaiAndTen error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ThietBi> findByMoAndTrangThaiAndMaThieBi(int idMo, int trangThaiKetNoi, String maThietBi, Pageable pageable) {
        try {
            return thietBiRepo.findByMoAndTrangThaiAndMaThieBi(idMo, trangThaiKetNoi, maThietBi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoAndTrangThaiAndMaThieBi error", ex.getCause());
            return null;
        }
    }

    @Override
    public Page<ThietBi> findByMoIdAndDoanhNghiepIdAndTrangThaiKetNoiAndTrangThai(int idMo, int idDoanhNghiep, Boolean trangThaiKetNoi, int trangThai, Pageable pageable) {
        try {
            return thietBiRepo.findByMoIdAndDoanhNghiepIdAndTrangThaiKetNoiAndTrangThai(idMo, idDoanhNghiep, trangThaiKetNoi, trangThai, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoIdAndDoanhNghiepIdAndTrangThaiKetNoiAndTrangThai error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ThietBi> findByMoId(int idMo, Pageable pageable) {
        try {
            return thietBiRepo.findByMoId(idMo, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ThietBi> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable) {
        try {
            return thietBiRepo.findByDoanhNghiepId(idDoanhNghiep, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByDoanhNghiepId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateTrangThaiKetNoiById(Integer idThietBi, int trangThai) {
        try {
            return thietBiRepo.updateTrangThaiKetNoiById(idThietBi, trangThai) > 0;
        } catch (Exception ex) {
            LOGGER.error("updateTrangThaiById error", ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTrangThaiKetNoiByMo(Integer moId, int trangThai) {
        try {
            return thietBiRepo.updateTrangThaiKetNoiByMo(moId, trangThai) > 0;
        } catch (Exception ex) {
            LOGGER.error("updateTrangThaiById error", ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<ThietBi> save(ThietBi thietBi) {
        try {
            return Optional.ofNullable(thietBiRepo.save(thietBi));
        } catch (Exception ex) {
            LOGGER.error("saveThietBi error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
