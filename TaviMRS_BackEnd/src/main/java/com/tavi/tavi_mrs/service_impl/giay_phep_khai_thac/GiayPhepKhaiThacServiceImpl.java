package com.tavi.tavi_mrs.service_impl.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.repository.giay_phep_khai_thac.GiayPhepKhaiThacRepo;
import com.tavi.tavi_mrs.service.giay_phep_khai_thac.GiayPhepKhaiThacService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiayPhepKhaiThacServiceImpl implements GiayPhepKhaiThacService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiayPhepKhaiThacServiceImpl.class);

    @Autowired
    private GiayPhepKhaiThacRepo giayPhepKhaiThacRepo;


    @Override
    public Optional<GiayPhepKhaiThac> findByIdGiayPhepAndXoa(int giayPhepId, boolean xoa) {
        try {
            return giayPhepKhaiThacRepo.findByIdGiayPhepAndXoa(giayPhepId, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdGiayPhepAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<GiayPhepKhaiThac> findByIdGiayPhepAndTrangThaiAndXoa(int idGiayPhep, int trangThai, boolean xoa) {
        try {
            return giayPhepKhaiThacRepo.findByIdGiayPhepAndTrangThaiAndXoa(idGiayPhep, trangThai, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdGiayPhepAndTrangThaiAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<GiayPhepKhaiThac> findByMoAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(int idMo, int loaiGiayPhepId, int trangThai, int thamQuyenCap, Pageable pageable) {
        try {
            return giayPhepKhaiThacRepo.findByMoAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(idMo, loaiGiayPhepId, trangThai, thamQuyenCap, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<GiayPhepKhaiThac> findByDoanhNghiepAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(int idDoanhNghiep, int idLoaiGiayPhep, int trangThai, int thamQuyenCap, Pageable pageable) {
        try {
            return giayPhepKhaiThacRepo.findByDoanhNghiepAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(idDoanhNghiep, idLoaiGiayPhep, trangThai, thamQuyenCap, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByDoanhNghiepAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<Integer, List<GiayPhepKhaiThac>> abstractReport(Date ngauDau, Date ngayCuoi, List<Integer> khoangSanIds, int huyenId) {
        try {
            boolean allKhoangSan = khoangSanIds != null && khoangSanIds.get(0) == 0;
            return giayPhepKhaiThacRepo.abstractReport(ngauDau, ngayCuoi, allKhoangSan, khoangSanIds, huyenId)
                    .stream().collect(Collectors.groupingBy(GiayPhepKhaiThac::getThamQuyenCap));
        } catch (Exception ex) {
            LOGGER.error("abstractReport error", ex);
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public Optional<GiayPhepKhaiThac> save(GiayPhepKhaiThac giayPhepKhaiThac) {
        try {
            return Optional.of(giayPhepKhaiThacRepo.save(giayPhepKhaiThac));
        } catch (Exception ex) {
            LOGGER.error("saveGiayPhepKhaiThac error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int idGiayPhepKhaiThac) {
        return false;
    }

    @Override
    public Page<GiayPhepKhaiThac> findAllToPage(Pageable pageable) {
        try {
            return giayPhepKhaiThacRepo.findAllToPage(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<GiayPhepKhaiThac> findByIdMoAndLoaiGiayPhepIdAndTrangThaiAndThamQuyenCapAndSoQuyetDinh(int idMo, int loaiGiayPhepId, int trangThai, int thamQuyenCap, String soQuyetDinh, Pageable pageable) {
        try {
            return giayPhepKhaiThacRepo.findByIdMoAndLoaiGiayPhepIdAndTrangThaiAndThamQuyenCapAndSoQuyetDinh(idMo, loaiGiayPhepId, trangThai, thamQuyenCap, soQuyetDinh, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByIdMoAndLoaiGiayPhepIdAndTrangThaiAndThamQuyenCapAndSoQuyetDinh error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<GiayPhepKhaiThac> findAll(Pageable pageable) {
        try {
            return giayPhepKhaiThacRepo.findAll(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAll error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
