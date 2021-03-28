package com.tavi.tavi_mrs.service_impl.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
import com.tavi.tavi_mrs.repository.xe_van_chuyen.XeVanChuyenRepo;
import com.tavi.tavi_mrs.service.xe_van_chuyen.XeVanChuyenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class XeVanChuyenServiceImpl implements XeVanChuyenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(XeVanChuyenServiceImpl.class);

    @Autowired
    private XeVanChuyenRepo xeVanChuyenRepo;

    @Override
    public Optional<XeVanChuyen> findByIdXeVanChuyenAndXoa(int idXeVanChuyen, boolean xoa) {
        try {
            return xeVanChuyenRepo.findByIdXeVanChuyenAndXoa(idXeVanChuyen, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdXeVanChuyenAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<XeVanChuyen> findAll(Pageable pageable) {
        try {
            return xeVanChuyenRepo.findAll(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<XeVanChuyen> findByDoanhNghiepId(int doanhNghiepId, Pageable pageable) {
        try {
            return xeVanChuyenRepo.findByDoanhNghiepId(doanhNghiepId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllByDoanhNghiepId error", ex.getCause());
            return null;
        }
    }

    @Override
    public Page<XeVanChuyen> findByTrangThai( Pageable pageable) {
        try {
            return xeVanChuyenRepo.findByTrangThai(pageable);
        } catch (Exception ex) {
            LOGGER.error("findByTrangThai error", ex.getCause());
            return null;
        }
    }

    @Override
    public Page<XeVanChuyen> findByDoanhNghiepIdAndBienSoXe( int idDoanhNghiep, String bienSoXe, Pageable pageable) {
        try {
            return xeVanChuyenRepo.findByBienSoXeAnddAndDoanhNghiep(idDoanhNghiep, bienSoXe, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByBienSoXeAnddAndDoanhNghiep error", ex.getCause());
            return null;
        }
    }

    @Override
    public Optional<XeVanChuyen> save(XeVanChuyen xeVanChuyen) {
        try {
            return Optional.ofNullable(xeVanChuyenRepo.save(xeVanChuyen));
        } catch (Exception ex) {
            LOGGER.error("saveXeVanChuyen error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<XeVanChuyen> findByBienSoXeAndTrangThai(String bienSoXe, int trangThai, Pageable pageable) {
        try {
            return xeVanChuyenRepo.findByBienSoXeAndTrangThai(bienSoXe, trangThai, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByDoanhNghiepIdAndBienSoXeAndTrangThai", ex.getCause());
            return null;
        }
    }

    @Override
    public Page<XeVanChuyen> findByTrangThaiAndDoanhNghiepAndBienSoXe(int doanhNghiepId, String bienSoXe, Pageable pageable) {
        try {
            return xeVanChuyenRepo.findByTrangThaiAndDoanhNghiepAndBienSoXe(doanhNghiepId, bienSoXe, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByTrangThaiAndDoanhNghiepAndBienSoXe", ex.getCause());
            return null;
        }
    }
}
