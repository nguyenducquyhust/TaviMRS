package com.tavi.tavi_mrs.service_impl.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhViPham;
import com.tavi.tavi_mrs.repository.hinh_anh.HinhAnhViPhamRepo;
import com.tavi.tavi_mrs.service.hinh_anh.HinhAnhViPhamService;
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
public class HinhAnhViPhamServiceImpl implements HinhAnhViPhamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HinhAnhViPhamServiceImpl.class);

    @Autowired
    private HinhAnhViPhamRepo hinhAnhViPhamRepo;

    @Override
    public Optional<HinhAnhViPham> findByIdHinhAnhViPham(int idHinhAnhViPham) {
        try {
            return hinhAnhViPhamRepo.findByIdHinhAnhViPham(idHinhAnhViPham);
        } catch (Exception ex) {
            LOGGER.error("findByIdHinhAnhViPham error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }



    @Override
    public Page<HinhAnhViPham> findByViPhamAndThoiGian(int idViPham, Date ngayDau, Date ngayCuoi, Pageable pageable) {
        try {
            return hinhAnhViPhamRepo.findByViPhamAndThoiGian(idViPham, ngayDau, ngayCuoi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByViPhamAndThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public Page<HinhAnhViPham> findByViPhamId(int idViPham, Pageable pageable) {
        try {
            return hinhAnhViPhamRepo.findByViPhamId(idViPham, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByViPhamId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HinhAnhViPham> findByViPhamAll(int idViPham) {
        try {
            return hinhAnhViPhamRepo.findByViPhamAll(idViPham);
        } catch (Exception ex) {
            LOGGER.error("findByViPhamIdAll error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<HinhAnhViPham> findAllOrderByThoiGian(Pageable pageable, boolean sort) {
        try {
            if (sort) return hinhAnhViPhamRepo.findAllOrOrderByThoiGianAsc(pageable);
            return hinhAnhViPhamRepo.findAllOrOrderByThoiGianDesc(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllOrderByThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<HinhAnhViPham> findByDoanhNghiepAndLoaiViPhamAndThoiGianOrderByThoiGian(int idDoanhNghiep, int idLoaiViPham, Date ngayDau, Date ngayCuoi, Pageable pageable, boolean sort) {
        try {
            if (sort) return hinhAnhViPhamRepo.findByDoanhNghiepAndLoaiViPhamAndThoiGianOrderByThoiGianAsc(idDoanhNghiep, idLoaiViPham, ngayDau, ngayCuoi, pageable);
            return hinhAnhViPhamRepo.findByDoanhNghiepAndLoaiViPhamAndThoiGianOrderByThoiGianDesc(idDoanhNghiep, idLoaiViPham, ngayDau, ngayCuoi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllOrderByThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Page<HinhAnhViPham> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable) {
//        try {
//            return hinhAnhViPhamRepo.findByDoanhNghiepId(idDoanhNghiep, pageable);
//        } catch (Exception ex) {
//            LOGGER.error("findByDoanhNghiepId error", ex);
//            ex.printStackTrace();
//            return null;
//        }
//    }

}
