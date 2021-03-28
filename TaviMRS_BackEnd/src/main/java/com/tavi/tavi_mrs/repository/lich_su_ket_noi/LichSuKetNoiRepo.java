package com.tavi.tavi_mrs.repository.lich_su_ket_noi;

import com.tavi.tavi_mrs.entities.common.TrangThaiLichSuKetNoi;
import com.tavi.tavi_mrs.entities.common.TrangThaiXuLy;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LichSuKetNoiRepo extends JpaRepository<LichSuKetNoi, Integer> {

    Optional<LichSuKetNoi> findByIdLichSuKetNoiAndXoa(int idLichSuKetNoi , boolean xoa);

    @Query(value = "from LichSuKetNoi l " +
            "where l.xoa = false " +
            "and l.trangThaiXuLy = ?#{T(com.tavi.tavi_mrs.entities.common.TrangThaiXuLy).CHUA_XU_LY} " +
            "and l.mo.trangThaiHoatDong = ?#{T(com.tavi.tavi_mrs.entities.common.TrangThaiHoatDongMo).DANG_HOAT_DONG}")
    List<LichSuKetNoi> findCanhBaoManHinhTheoDoi();

    @Query(value = "from LichSuKetNoi l where (0 = ?1 or l.trangThaiXuLy = ?1) and l.xoa = false")
    List<LichSuKetNoi> findByTrangThaiXuLy(int trangThaiXuLy);

    @Query(value = "from LichSuKetNoi l where (l.mo.idMo = ?1) and (0 = ?1 or l.trangThaiXuLy = ?2) and l.xoa = false")
    List<LichSuKetNoi> findByMoAndTrangThaiXuLy(Integer moId, int trangThaiXuLy);

    @Query(value = "from LichSuKetNoi l where (0 = ?1 or l.mo.idMo = ?1) and l.xoa = false order by l.trangThaiXuLy asc, l.thoiGianXayRa desc ")
    Page<LichSuKetNoi> findByMo(Integer moId, Pageable pageable);

    @Query(value = "select count(l) from LichSuKetNoi l " +
            "where l.xoa = false " +
            "and l.mo.idMo = ?1 " +
            "and l.trangThai = ?#{T(com.tavi.tavi_mrs.entities.common.TrangThaiLichSuKetNoi).MAT_KET_NOI_CHUA_PHUC_HOI} " +
            "and (l.thoiGianKetNoiLai is null " +
            "or l.trangThaiXuLy <> ?#{T(com.tavi.tavi_mrs.entities.common.TrangThaiXuLy).DA_XU_LY})")
    Integer existDangXuLyOrChuaXuLy(Integer moId);

    @Transactional
    @Modifying
    @Query("update LichSuKetNoi l set l.trangThai = ?3, l.thoiGianKetNoiLai = ?4, l.trangThaiXuLy = ?5 where l.mo.idMo = ?1 and l.trangThai = ?2")
    int updateThoiGianKetNoiLaiByMoAndTrangThai(Integer moId, int trangThai, int trangThaiMoi, Date thoiGianKetNoiLai, int trangThaiXuLy);
}
