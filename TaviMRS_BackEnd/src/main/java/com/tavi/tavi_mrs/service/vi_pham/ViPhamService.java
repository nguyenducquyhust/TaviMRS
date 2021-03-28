package com.tavi.tavi_mrs.service.vi_pham;

import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ViPhamService {

    Optional<ViPham> findByIdViPhamAndXoa(int viPhamId, boolean xoa);

    Page<ViPham> findByMo(Integer moId, Pageable pageable);

    List<ViPham> findByTrangThaiXuLy(int trangThaiXuLy);

    List<ViPham> findByMoAndTrangThaiXuLy(Integer idMo, int trangThaiXuLy);

    Page<ViPham> findByMoAndThoiGianTaoAndLoaiViPham(int moId, Date thoiGianTu, Date thoiGianDen, int loaiViPhamId, Pageable pageable);

    List<ViPham> findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(int doanhNghiepId, Date thoiGianTu, Date thoiGianDen, int loaiViPhamId);

    List<ViPham> findByTrangThaiXuLyAndLoaiViPham(int trangThaiXuLy, Integer idLoaiViPham);

    Set<LoaiViPham> findLoaiViPhamByMoAndTrangThaiXuLy(Integer moId, int trangThaiXuLy);

    Integer countByMoAndTrangThai(Integer moId, int trangThaiXuLy);

    Optional<ViPham> save(ViPham viPham);
}
