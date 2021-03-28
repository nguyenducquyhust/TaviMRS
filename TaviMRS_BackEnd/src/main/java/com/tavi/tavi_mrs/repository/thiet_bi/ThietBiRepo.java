package com.tavi.tavi_mrs.repository.thiet_bi;

import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ThietBiRepo extends JpaRepository<ThietBi, Integer> {

    Optional<ThietBi> findByIdThietBiAndXoa(int idThietBi, boolean xoa);

    Page<ThietBi> findAll(Pageable pageable);

    @Query(value = "from ThietBi t " +
            "where " +
            "(0 = ?1 or t.mo.idMo = ?1 and t.mo.xoa = false) " +
            "and (0 = ?2 or t.trangThai = ?2) " +
            "and (?3 is null or t.tenThietBi = ?3)" +
            "and t.xoa = false " +
            "order by t.idThietBi asc ")
    Page<ThietBi> findByMoAndTrangThaiAndTen(int idMo, int trangThai, String tenThietBi, Pageable pageable);

    @Query(value = "from ThietBi t " +
            "where " +
            "(0 = ?1 or t.mo.idMo = ?1 and t.mo.xoa = false) " +
            "and (-1 = ?2 or t.trangThaiKetNoi = ?2)  " +
            "and t.maThietBi like concat('%', ?3, '%') " +
            "and t.xoa = false " +
            "order by t.idThietBi asc ")
    Page<ThietBi> findByMoAndTrangThaiAndMaThieBi(int idMo, int trangThaiKetNoi, String  maThietBi, Pageable pageable);

    @Query(value = "from ThietBi t " +
            "where " +
            "(0 = ?1 or t.mo.idMo = ?1 and t.mo.xoa = false) " +
            "and (0 = ?2 or t.doanhNghiep.idDoanhNghiep = ?2 and t.doanhNghiep.xoa = false) " +
            "and (?3 is null or t.trangThaiKetNoi = ?3)" +
            "and (0 = ?4 or t.trangThai = ?4) " +
            "and t.xoa = false " +
            "order by t.idThietBi asc ")
    Page<ThietBi> findByMoIdAndDoanhNghiepIdAndTrangThaiKetNoiAndTrangThai(int idMo, int idDoanhNghiep, Boolean trangThaiKetNoi, int trangThai, Pageable pageable);

    @Query(value = "from ThietBi t where (0 = ?1 or t.mo.idMo = ?1 and t.mo.xoa = false) and t.xoa = false order by t.idThietBi asc ")
    Page<ThietBi> findByMoId(int idMo, Pageable pageable);

    @Query(value = "from ThietBi t where (0 = ?1 or t.doanhNghiep.idDoanhNghiep = ?1 and t.doanhNghiep.xoa = false) and t.xoa = false order by t.idThietBi asc ")
    Page<ThietBi> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ThietBi t set t.trangThaiKetNoi = (?1) where t.doanhNghiep.idDoanhNghiep in (?2)")
    int updateTrangThaiKetNoiByDoanhNghiep(int trangThai, int doanhNghiepId);

    @Transactional
    @Modifying
    @Query("update ThietBi t set t.trangThaiKetNoi = ?2 where t.idThietBi = ?1 and t.xoa = false")
    int updateTrangThaiKetNoiById(int idThietBi, int trangThai);

    @Transactional
    @Modifying
    @Query("update ThietBi t set t.trangThaiKetNoi = ?2 where t.mo.idMo = ?1 and t.xoa = false")
    int updateTrangThaiKetNoiByMo(int idThietBi, int trangThai);
}
