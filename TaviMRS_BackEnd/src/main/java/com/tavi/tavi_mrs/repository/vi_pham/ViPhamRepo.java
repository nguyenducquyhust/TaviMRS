package com.tavi.tavi_mrs.repository.vi_pham;

import com.tavi.tavi_mrs.entities.common.TrangThaiHoatDongMo;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViPhamRepo extends JpaRepository<ViPham, Integer> {

    Optional<ViPham> findByIdViPhamAndXoa(Integer idViPham, Boolean xoa);

    @Query(value = "from ViPham l " +
            "where l.xoa = false " +
            "and l.trangThaiXuLy = ?#{T(com.tavi.tavi_mrs.entities.common.TrangThaiXuLy).CHUA_XU_LY} " +
            "and l.mo.trangThaiHoatDong = ?#{T(com.tavi.tavi_mrs.entities.common.TrangThaiHoatDongMo).DANG_HOAT_DONG}")
    List<LichSuKetNoi> findCanhBaoManHinhTheoDoi();

    @Query(value = "from ViPham l where (0 = ?1 or l.mo.idMo = ?1) and l.xoa = false order by l.trangThaiXuLy asc, l.thoiGianXayRa desc ")
    Page<ViPham> findByMo(Integer moId, Pageable pageable);

    @Query(value = "from ViPham p where (0 = ?1 or p.trangThaiXuLy = ?1) and p.xoa = false")
    List<ViPham> findByTrangThaiXuLy(int trangThaiXuLy);

    @Query(value = "from ViPham p " +
            "where (0 = ?1 or p.mo.idMo = ?1) " +
            "and (0 = ?2 or p.trangThaiXuLy = ?2) " +
            "and p.mo.trangThaiHoatDong = ?#{T(com.tavi.tavi_mrs.entities.common.TrangThaiHoatDongMo).DANG_HOAT_DONG} " +
            "and p.xoa = false " +
            "order by p.trangThaiXuLy asc, p.thoiGianXayRa desc")
    List<ViPham> findByMoAndTrangThaiXuLy(Integer moId, int trangThaiXuLy);

    @Query(value = "from ViPham v " +
            "where " +
            "(0 = ?1 or v.mo.idMo = ?1) " +
            "and (?2 is null or v.thoiGianXayRa >= ?2 ) and (?3 is null or v.thoiGianXayRa <= ?3)"+
            "and (0 = ?4 or v.loaiViPham.idLoaiViPham = ?4 ) " +
            "and v.xoa = false " +
            "order by v.thoiGianXayRa, v.thoiGianThongBao")
    Page<ViPham> findByMoAndThoiGianTaoAndLoaiViPham(int moId, Date thoiGianTu, Date thoiGianDen, int loaiViPhamId, Pageable pageable);

    @Query(value = "from ViPham v " +
            "where " +
            "(0 = ?1 or v.doanhNghiep.idDoanhNghiep = ?1) " +
            "and (?2 is null or v.thoiGianXayRa >= ?2 ) and (?3 is null or v.thoiGianXayRa <= ?3)"+
            "and (0 = ?4 or v.loaiViPham.idLoaiViPham = ?4 ) " +
            "and v.xoa = false " +
            "order by v.thoiGianXayRa desc ")
    List<ViPham> findByDoanhNghiepAndThoiGianTaoAndLoaiViPham(int doanhNghiepId, Date thoiGianTu, Date thoiGianDen, int loaiViPhamId);

    @Query(value = "from ViPham p where (0 = ?1 or p.trangThaiXuLy = ?1) and (0 = ?2 or p.loaiViPham.idLoaiViPham = ?2) and p.xoa = false")
    List<ViPham> findByTrangThaiXuLyAndLoaiViPham(int trangThaiXuLy, Integer idLoaiViPham);

    @Query(value = "select count(v) from ViPham v where v.mo.idMo = ?1 and v.trangThaiXuLy = ?2 and v.xoa = false")
    int countByMoAndTrangThai(Integer moId, int trangThaiXuLy);
}
