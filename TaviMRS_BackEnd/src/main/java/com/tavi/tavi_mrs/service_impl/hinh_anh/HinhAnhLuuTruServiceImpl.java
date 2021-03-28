package com.tavi.tavi_mrs.service_impl.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.repository.hinh_anh.HinhAnhLuuTruRepo;
import com.tavi.tavi_mrs.service.hinh_anh.HinhAnhLuuTruService;
import com.tavi.tavi_mrs.service_impl.thiet_bi.ThietBiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class HinhAnhLuuTruServiceImpl implements HinhAnhLuuTruService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HinhAnhLuuTruServiceImpl.class);

    @Autowired
    private HinhAnhLuuTruRepo hinhAnhLuuTruRepo;

    @Override
    public Optional<HinhAnhLuuTru> findByIdHinhAnhAndXoa(int idHinhAnh, boolean xoa) {
        try {
            return hinhAnhLuuTruRepo.findByIdHinhAnhAndXoa(idHinhAnh, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdHinhAnhAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<HinhAnhLuuTru> findAllToPage(Pageable pageable) {
        try {
            return hinhAnhLuuTruRepo.findAllToPage(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<HinhAnhLuuTru> findAllOrderByThoiGian(Pageable pageable, boolean sort) {
        try {
            if (sort) return hinhAnhLuuTruRepo.findAllByOrOrderByThoiGianAsc(pageable);
            return hinhAnhLuuTruRepo.findAllByOrOrderByThoiGianDesc(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllOrderByThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<HinhAnhLuuTru> findByDoanhNghiepIdAndThoiGianOrderByThoiGian(int idDoanhNghiep, Date ngayDau, Date ngayCuoi, Pageable pageable, boolean sort) {
        try {
            if (sort) return hinhAnhLuuTruRepo.findByDoanhNghiepIdAndThoiGianOrderByThoiGianAsc(idDoanhNghiep, ngayDau, ngayCuoi, pageable);
            return hinhAnhLuuTruRepo.findByDoanhNghiepIdAndThoiGianOrderByThoiGianDesc(idDoanhNghiep, ngayDau, ngayCuoi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllOrderByThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<HinhAnhLuuTru> findByMoIdAndThoiGian(int idMo, Date ngayDau, Date ngayCuoi, Pageable pageable) {
        try {
            return hinhAnhLuuTruRepo.findByMoIdAndThoiGian(idMo, ngayDau, ngayCuoi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoIdAndThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<HinhAnhLuuTru> findByMoId(int idMo, Pageable pageable) {
        try {
            return hinhAnhLuuTruRepo.findByMoId(idMo, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMoId error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
