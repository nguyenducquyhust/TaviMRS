package com.tavi.tavi_mrs.repository.mo;

import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.mo.Mo;

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
public interface MoRepo extends JpaRepository<Mo, Integer> {

    Optional<Mo> findByIdMoAndXoa(int idMo, boolean xoa);

    @Query(value = "from Mo m where m.xoa = false order by m.idMo asc ")
    Page<Mo> findAllToPage(Pageable pageable);

    @Query(value = "from Mo m where (0 = ?1 or m.doanhNghiep.idDoanhNghiep = ?1) and m.xoa = false order by m.idMo asc ")
    Page<Mo> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable);

    @Query(value = "from Mo m where (0 = ?1 or m.trangThaiHoatDong = ?1) and m.xoa = false")
    List<Mo> findByTrangThaiHoatDong(int trangThaiHoatDong);

    @Query(value = "select m from Mo m " +
            "where m.xoa = false " +
            "and (?1 = true or m.khoangSan.idKhoangSan in (?2))" +
            "and (?3 is null or m.doanhNghiep.tenDoanhNghiep like concat('%', ?3, '%')) " +
            "and (0 = ?4 or m.doanhNghiep.huyenId = ?4) " +
            "order by m.doanhNghiep")
    Page<Mo> findPageByKhoangSanIdsAndThongTinDoanhNghiep(boolean allKhoangSan, List<Integer> khoangSanIds, String ten, Integer huyenId, Pageable pageable);

    @Query(value = "from Mo m where (?1 = true and m.khoangSan.xoa = false or m.khoangSan.idKhoangSan in (?2)) and m.xoa = false order by m.idMo asc ")
    List<Mo> findListByKhoangSanIds(boolean allKhoangSan, List<Integer> khoangSanIds);

    @Query(value = "from Mo m " +
            "where " +
            "(?1 = true or m.khoangSan.idKhoangSan in (?2)) " +
            "and (0 = ?3 or m.doanhNghiep.idDoanhNghiep = ?3) " +
            "and m.diaChi like concat('%', ?4, '%') " +
            "and (0 = ?5 or m.tinhId = ?5) " +
            "and (0 = ?6 or m.huyenId = ?6) " +
            "and m.xoa = false " +
            "order by m.idMo asc ")
    Page<Mo> findByKhoangSanIdsAndDoanhNghiepIdAndDiaChiAndTinhIdAndHuyenId(boolean allKhoangSan, List<Integer> khoangSanIds, int idDoanhNghiep, String diaChi, int tinhId, int huyenId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Mo m set m.trangThaiKetNoi = ?2 where m.idMo = ?1 and m.xoa = false")
    int updateTrangThaiKetNoiByIdMo(Integer idMo, int trangThaiKetNoi);

}
