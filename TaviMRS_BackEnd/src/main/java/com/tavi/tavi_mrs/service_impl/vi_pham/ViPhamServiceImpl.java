package com.tavi.tavi_mrs.service_impl.vi_pham;

import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import com.tavi.tavi_mrs.repository.vi_pham.ViPhamRepo;
import com.tavi.tavi_mrs.service.vi_pham.ViPhamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ViPhamServiceImpl implements ViPhamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViPhamServiceImpl.class);

    @Autowired
    private ViPhamRepo viPhamRepo;

    @Override
    public Optional<ViPham> findByIdViPhamAndXoa(int viPhamId, boolean xoa) {
        try {
            return viPhamRepo.findByIdViPhamAndXoa(viPhamId, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdViPhamAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<ViPham> findByMo(Integer moId, Pageable pageable) {
        try {
            return viPhamRepo.findByMo(moId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByMo error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ViPham> findByMoAndThoiGianTaoAndLoaiViPham(int moId, Date thoiGianTu, Date thoiGianDen, int loaiViPhamId, Pageable pageable) {
        try {
            return viPhamRepo.findByMoAndThoiGianTaoAndLoaiViPham(moId, thoiGianTu, thoiGianDen, loaiViPhamId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByIdViPhamAndXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ViPham> findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(int doanhNghiepId, Date thoiGianTu, Date thoiGianDen, int loaiViPhamId) {
        try {
            return viPhamRepo.findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(doanhNghiepId, thoiGianTu, thoiGianDen, loaiViPhamId);
        } catch (Exception ex) {
            LOGGER.error("findByIdViPhamAndXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ViPham> findByTrangThaiXuLy(int trangThaiXuLy) {
        try {
            return viPhamRepo.findByTrangThaiXuLy(trangThaiXuLy);
        } catch (Exception ex) {
            LOGGER.error("findByTrangThaiXuLy error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ViPham> findByMoAndTrangThaiXuLy(Integer idMo, int trangThaiXuLy) {
        try {
            return viPhamRepo.findByMoAndTrangThaiXuLy(idMo, trangThaiXuLy);
        } catch (Exception ex) {
            LOGGER.error("findByTrangThaiXuLy error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ViPham> findByTrangThaiXuLyAndLoaiViPham(int trangThaiXuLy, Integer idLoaiViPham) {
        try {
            return viPhamRepo.findByTrangThaiXuLyAndLoaiViPham(trangThaiXuLy, idLoaiViPham);
        } catch (Exception ex) {
            LOGGER.error("findByTrangThaiXuLyAndLoaiViPham error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<LoaiViPham> findLoaiViPhamByMoAndTrangThaiXuLy(Integer moId, int trangThaiXuLy) {
        try {
            return viPhamRepo.findByMoAndTrangThaiXuLy(moId, trangThaiXuLy).stream().collect(Collectors.groupingBy(ViPham::getLoaiViPham)).keySet();
        } catch (Exception ex) {
            LOGGER.error("findLoaiViPhamByMoAndTrangThaiXuLy error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer countByMoAndTrangThai(Integer moId, int trangThaiXuLy) {
        try {
            return viPhamRepo.countByMoAndTrangThai(moId, trangThaiXuLy);
        } catch (Exception ex) {
            LOGGER.error("countByMoAndTrangThai error", ex);
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<ViPham> save(ViPham viPham) {
        try {
            return Optional.ofNullable(viPhamRepo.save(viPham));
        } catch (Exception ex) {
            LOGGER.error("saveViPham error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
