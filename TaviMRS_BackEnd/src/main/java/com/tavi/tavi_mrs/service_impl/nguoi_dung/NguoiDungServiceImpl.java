package com.tavi.tavi_mrs.service_impl.nguoi_dung;


import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import com.tavi.tavi_mrs.repository.nguoi_dung.NguoiDungRepo;
import com.tavi.tavi_mrs.service.nguoi_dung.NguoiDungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NguoiDungServiceImpl.class);

    @Autowired
    private NguoiDungRepo nguoiDungRepo;

    @Override
    public Optional<NguoiDung> findByIdNguoiDungAndXoa(int idNguoiDung, boolean xoa) {
        try {
            return nguoiDungRepo.findByIdNguoiDungAndXoa(idNguoiDung, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdNguoiDungAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<NguoiDung> findByMoId(int idMo, Pageable pageable) {
        try {
            return nguoiDungRepo.findByMoId(idMo, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoId error", ex.getCause());
            return null;
        }
    }

    @Override
    public Page<NguoiDung> findAll(Pageable pageable) {
        try {
            return nguoiDungRepo.findAll( pageable);
        } catch (Exception ex) {
            LOGGER.error("findAll error", ex.getCause());
            return null;
        }
    }

    @Override
    public List<NguoiDung> findAll() {
        try {
            return nguoiDungRepo.findAll();
        } catch (Exception ex) {
            LOGGER.error("findAllList error", ex.getCause());
            return null;
        }
    }

    @Override
    public Page<NguoiDung> findByDoanhNghiepId(int doanhNghiepId, Pageable pageable) {
        try {
            return nguoiDungRepo.findByDoanhNghiepId(doanhNghiepId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByDoanhNghiepId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<NguoiDung> findByTaiKhoanAndDoanhNghiepAndTrangThai(String tenDangNhap, int idDoanhNghiep, int trangThai, Pageable pageable) {
        try {
            return nguoiDungRepo.findByTaiKhoanAndDoanhNghiepAndTrangThai(tenDangNhap, idDoanhNghiep, trangThai, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByTaiKhoanAndDoanhNghiepAndTrangThai error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<NguoiDung> save(NguoiDung nguoiDung) {
        try {
            return Optional.ofNullable(nguoiDungRepo.save(nguoiDung));
        } catch (Exception ex) {
            LOGGER.error("saveNguoiDung error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<NguoiDung> findByMoIdAndTenDangNhapAndSoDienThoaiAndChucVuAndTrangThai(int moId, String tenDangNhap, String soDienThoai, String chucVu, int trangThai, Pageable pageable) {
        try {
            return nguoiDungRepo.findByMoIdAndTenDangNhapAndSoDienThoaiAndChucVuAndTrangThai(moId, tenDangNhap, soDienThoai, chucVu, trangThai, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoIdAndTenDangNhapAndSoDienThoaiAndChucVuAndTrangThai error", ex);
            ex.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Page<NguoiDung> findByTaiKhoanAndDoanhNghiepAndTrangThai(String tenDangNhap, int idDoanhNghiep, int trangThai, Pageable pageable) {
//        try {
//            return nguoiDungRepo.findByTaiKhoanAndDoanhNghiepAndTrangThai(tenDangNhap, idDoanhNghiep, trangThai, pageable);
//        } catch (Exception ex) {
//            LOGGER.error("findByTaiKhoanAndDoanhNghiepAndTrangThai error", ex.getCause());
//            return null;
//        }
//    }
}
