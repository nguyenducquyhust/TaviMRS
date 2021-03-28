package com.tavi.tavi_mrs.service_impl.mo;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.mo.NhatKyKhaiThac;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTruLuongKhaiThac;
import com.tavi.tavi_mrs.repository.mo.NhatKyKhaiThacRepo;
import com.tavi.tavi_mrs.service.mo.NhatKyKhaiThacService;
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
public class NhatKyKhaiThacServiceImpl implements NhatKyKhaiThacService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NhatKyKhaiThacServiceImpl.class);

    @Autowired
    private NhatKyKhaiThacRepo nhatKyKhaiThacRepo;

    @Override
    public Optional<NhatKyKhaiThac> findByIdNhatKyKhaiThac(int idNhatKyKhaiThac) {
        try {
            return nhatKyKhaiThacRepo.findByIdNhatKyKhaiThac(idNhatKyKhaiThac);
        } catch (Exception ex) {
            LOGGER.error("findByIdNhatKyKhaiThac error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<NhatKyKhaiThac> findByMoAndNgay(int moId, Date thoiGianTu, Date thoiGianDen , Pageable pageable) {
        try {
            return nhatKyKhaiThacRepo.findByMoAndNgay(moId, thoiGianTu, thoiGianDen , pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoAndNgay error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BieuDo> abstractBieuDoByDoanhNghiep(Date ngayDau, Date ngayCuoi) {
        try {
            return nhatKyKhaiThacRepo.abstractBieuDoByDoanhNghiep(ngayDau, ngayCuoi);
        } catch (Exception ex) {
            LOGGER.error("abstractBieuDoByDoanhNghiep error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BieuDo> abstractBieuDoByKhoangSan(Date ngayDau, Date ngayCuoi) {
        try {
            return nhatKyKhaiThacRepo.abstractBieuDoByKhoangSan(ngayDau, ngayCuoi);
        } catch (Exception ex) {
            LOGGER.error("abstractBieuDoByKhoangSan error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ThongKeTruLuongKhaiThac> abstractThongKeTruLuongKhaiThac(Date ngayDau, Date ngayCuoi, List<Integer> khoangSanIds, Integer huyenId) {
        try {
            boolean allKhoangSan = khoangSanIds != null && khoangSanIds.get(0) == 0;
            return nhatKyKhaiThacRepo.abstractThongKeTruLuongKhaiThac(ngayDau, ngayCuoi, allKhoangSan, khoangSanIds, huyenId);
        } catch (Exception ex) {
            LOGGER.error("abstractThongKeTruLuongKhaiThac error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
