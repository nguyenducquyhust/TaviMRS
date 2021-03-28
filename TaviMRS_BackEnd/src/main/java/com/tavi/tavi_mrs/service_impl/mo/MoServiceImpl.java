package com.tavi.tavi_mrs.service_impl.mo;

import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.repository.mo.MoRepo;
import com.tavi.tavi_mrs.service.mo.MoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@Service
public class MoServiceImpl implements MoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoServiceImpl.class);

    @Autowired
    private MoRepo moRepo;

    @Override
    public Optional<Mo> findByIdMoAndXoa(int idMo, boolean xoa) {
        try {
            return moRepo.findByIdMoAndXoa(idMo, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdMoAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<Mo> findAllToPage(Pageable pageable) {
        try {
            return moRepo.findAllToPage(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Mo> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable) {
        try {
            return moRepo.findByDoanhNghiepId(idDoanhNghiep, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByDoanhNghiepId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mo> findByTrangThaiHoatDong(int trangThaiHoatDong) {
        try {
            return moRepo.findByTrangThaiHoatDong(trangThaiHoatDong);
        } catch (Exception ex) {
            LOGGER.error("findByTrangThaiHoatDong error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Mo> findPageByKhoangSanIdsAndThongTinDoanhNghiep(List<Integer> khoangSanIds, String name, Integer huyenId, Pageable pageable) {
        try {
            boolean allKhoangSan = khoangSanIds == null || khoangSanIds.get(0) == 0;
            return moRepo.findPageByKhoangSanIdsAndThongTinDoanhNghiep(allKhoangSan, khoangSanIds, name, huyenId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findPageByKhoangSanIdsAndThongTinDoanhNghiep error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mo> findListByKhoangSanIds(List<Integer> khoangSanIds) {
        try {
            boolean allKhoangSan = khoangSanIds != null && khoangSanIds.get(0) == 0;
            return moRepo.findListByKhoangSanIds(allKhoangSan, khoangSanIds);
        } catch (Exception ex) {
            LOGGER.error("findListByKhoangSanIds error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Mo> findByKhoangSanIdsAndDoanhNghiepIdAndDiaChiAndTinhIdAndHuyenId(List<Integer> khoangSanIds, int idDoanhNghiep, String diaChi, int tinhId, int huyenId, Pageable pageable) {
        try {
            boolean allKhoangSan = khoangSanIds != null && khoangSanIds.get(0) == 0;
            return moRepo.findByKhoangSanIdsAndDoanhNghiepIdAndDiaChiAndTinhIdAndHuyenId(allKhoangSan, khoangSanIds, idDoanhNghiep, diaChi, tinhId, huyenId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByKhoangSanIdsAndDoanhNghiepIdAndDiaChiAndTinhIdAndHuyenId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Mo> save(Mo mo) {
        try {
            return Optional.ofNullable(moRepo.save(mo));
        } catch (Exception ex) {
            LOGGER.error("saveMo error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Boolean updateTrangThaiKetNoiByIdMo(Integer moId, int trangThaiKetNoi) {
        try {
            return moRepo.updateTrangThaiKetNoiByIdMo(moId, trangThaiKetNoi) > 0;
        } catch (Exception ex) {
            LOGGER.error("updateTrangThaiKetNoiByIdMo error", ex);
            ex.printStackTrace();
            return false;
        }
    }
}
