package com.tavi.tavi_mrs.service_impl.doanh_nghiep;

import com.tavi.tavi_mrs.entities.common.TrangThaiDoanhNghiep;
import com.tavi.tavi_mrs.entities.common.TrangThaiNguoiDung;
import com.tavi.tavi_mrs.entities.common.TrangThaiThietBi;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.repository.doanh_nghiep.DoanhNghiepRepo;
import com.tavi.tavi_mrs.repository.nguoi_dung.NguoiDungRepo;
import com.tavi.tavi_mrs.repository.thiet_bi.ThietBiRepo;
import com.tavi.tavi_mrs.service.doanh_nghiep.DoanhNghiepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoanhNghiepServiceImpl implements DoanhNghiepService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoanhNghiepServiceImpl.class);

    @Autowired
    private DoanhNghiepRepo doanhNghiepRepo;

    @Autowired
    private NguoiDungRepo nguoiDungRepo;

    @Autowired
    private ThietBiRepo thietBiRepo;

    @Override
    public Optional<DoanhNghiep> findByIdDoanhNghiepAndXoa(int idDoanhNghiep, boolean xoa) {
        try {
            return doanhNghiepRepo.findByIdDoanhNghiepAndXoa(idDoanhNghiep, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdDoanhNghiepAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<DoanhNghiep> findAll(Pageable pageable) {
        try {
            return doanhNghiepRepo.findAll(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAll error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<DoanhNghiep> save(DoanhNghiep doanhNghiep) {
        try {
            return Optional.ofNullable(doanhNghiepRepo.save(doanhNghiep));
        } catch (Exception ex) {
            LOGGER.error("saveDoanhNghiep error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<DoanhNghiep> saveCauHinh(DoanhNghiep doanhNghiep) {
        try {
            switch (doanhNghiep.getTrangThaiPhanMem()) {
                case TrangThaiDoanhNghiep.HOAT_DONG:
                    nguoiDungRepo.updateTrangThaiByDoanhNghiep(TrangThaiNguoiDung.HOAT_DONG,doanhNghiep.getIdDoanhNghiep());
                    thietBiRepo.updateTrangThaiKetNoiByDoanhNghiep(TrangThaiThietBi.KHONG_KET_NOI,doanhNghiep.getIdDoanhNghiep());
                    break;
                case TrangThaiDoanhNghiep.KHOA_TAI_KHOAN:
                    nguoiDungRepo.updateTrangThaiByDoanhNghiep(TrangThaiNguoiDung.KHOA,doanhNghiep.getIdDoanhNghiep());
                    break;
                case TrangThaiDoanhNghiep.KHOA_THIET_BI:
                    thietBiRepo.updateTrangThaiKetNoiByDoanhNghiep(TrangThaiThietBi.KHOA_THIET_BI,doanhNghiep.getIdDoanhNghiep());
                    break;
                case TrangThaiDoanhNghiep.DUNG_HOAT_DONG:
                    nguoiDungRepo.updateTrangThaiByDoanhNghiep(TrangThaiNguoiDung.KHOA,doanhNghiep.getIdDoanhNghiep());
                    thietBiRepo.updateTrangThaiKetNoiByDoanhNghiep(TrangThaiThietBi.KHOA_THIET_BI,doanhNghiep.getIdDoanhNghiep());
                    break;
            }
            return Optional.ofNullable(doanhNghiepRepo.save(doanhNghiep));
        } catch (Exception ex) {
            LOGGER.error("saveCauHinhDoanhNghiep error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<DoanhNghiep> findByTenAndDiaChiAndTrangThaiPhanMem(String tenDoanhNghiep, String diaChi, int trangThaiPhanMem, Pageable pageable) {
        try {
            return doanhNghiepRepo.findByTenAndDiaChiAndTrangThaiPhanMem(tenDoanhNghiep, diaChi, trangThaiPhanMem, pageable);
        } catch (Exception ex) {
            LOGGER.error("FindByTenAndDiaChiAndTrangThaiPhanMem", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
