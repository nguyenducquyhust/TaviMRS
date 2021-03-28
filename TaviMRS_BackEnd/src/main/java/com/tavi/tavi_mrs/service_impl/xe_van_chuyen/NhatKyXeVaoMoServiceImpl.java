package com.tavi.tavi_mrs.service_impl.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTheoLuongXeVaoMo;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.NhatKyXeVaoMo;
import com.tavi.tavi_mrs.repository.xe_van_chuyen.NhatKyXeVaoMoRepo;
import com.tavi.tavi_mrs.service.xe_van_chuyen.NhatKyXeVaoMoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NhatKyXeVaoMoServiceImpl implements NhatKyXeVaoMoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NhatKyXeVaoMoServiceImpl.class);

    @Autowired
    private NhatKyXeVaoMoRepo nhatKyXeVaoMoRepo;

    @Override
    public Optional<NhatKyXeVaoMo> findByIdNhatKyXeVaoMo(int idNhatKyXeVaoMo) {
        try {
            return nhatKyXeVaoMoRepo.findByIdNhatKyXeVaoMo(idNhatKyXeVaoMo);
        } catch (Exception ex) {
            LOGGER.error("findByIdNhatKyXeVaoMo error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<NhatKyXeVaoMo> findByMo(int idMo, Pageable pageable) {
        try {
            return nhatKyXeVaoMoRepo.findByMo(idMo, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMo error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<NhatKyXeVaoMo> findByMoAndThoiGian(int idMo, Date ngayDau, Date ngayCuoi, Pageable pageable) {
        try {
            return nhatKyXeVaoMoRepo.findByMoAndThoiGian(idMo, ngayDau, ngayCuoi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoAndThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhatKyXeVaoMo> findByMoAndIdXeAndThoiGian(int idMo, int idXeVanChuyen, Date ngayDau, Date ngayCuoi) {
        try {
            return nhatKyXeVaoMoRepo.findByMoAndIdXeAndThoiGian(idMo,idXeVanChuyen, ngayDau, ngayCuoi);
        } catch (Exception ex) {
            LOGGER.error("findByMoAndIdXeAndThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhatKyXeVaoMo> findByDoanhNghiepAndThoiGian(int idDoanhNghiep, Date ngayDau, Date ngayCuoi) {
        try {
            return nhatKyXeVaoMoRepo.findByDoanhNghiepAndThoiGian(idDoanhNghiep, ngayDau, ngayCuoi);
        } catch (Exception ex) {
            LOGGER.error("findByDoanhNghiepAndThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> findTongKhaiThac(int xeVanChuyenId) {
        try {
            return nhatKyXeVaoMoRepo.findTongKhaiThac(xeVanChuyenId);
        } catch (Exception ex) {
            LOGGER.error("findTongKhaiThac error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int countByMoAndNgay(int idMo, Date ngay) {
        try {
            return nhatKyXeVaoMoRepo.countByMoAndNgay(idMo, ngay);
        } catch (Exception ex) {
            LOGGER.error("countByMoAndNgay error", ex);
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ThongKeTheoLuongXeVaoMo> abstractThongKeTheoLuongXeVaoMo(Date ngayDau, Date ngayCuoi, Integer doanhNghiepId) {
        try {
            return nhatKyXeVaoMoRepo.abstractThongKeTheoLuongXeVaoMo(ngayDau, ngayCuoi, doanhNghiepId);
        } catch (Exception ex) {
            LOGGER.error("abstractThongKeTheoLuongXeVaoMo error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
