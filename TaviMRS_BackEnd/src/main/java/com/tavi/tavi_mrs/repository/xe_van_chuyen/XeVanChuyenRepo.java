package com.tavi.tavi_mrs.repository.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface XeVanChuyenRepo extends JpaRepository<XeVanChuyen, Integer> {

    Optional<XeVanChuyen> findByIdXeVanChuyenAndXoa(int xeVanChuyenId, boolean xoa);

    @Query(value = "select x from XeVanChuyen x where x.xoa = false")
    Page<XeVanChuyen> findAll(Pageable pageable);

    @Query(value = "select x from XeVanChuyen x where (0 = ?1 or x.doanhNghiep.idDoanhNghiep = ?1) and x.xoa = false")
    Page<XeVanChuyen> findByDoanhNghiepId(int doanhNghiepId, Pageable pageable);

    @Query(value = "from XeVanChuyen x " +
            "where " +
            "(x.bienSoXe like concat('%', ?1, '%')) " +
            "and (-1 = ?2 or x.trangThai = ?2) " +
            "and x.xoa = false " +
            "order by x.idXeVanChuyen asc ")
    Page<XeVanChuyen> findByBienSoXeAndTrangThai(String bienSoXe, int trangThai, Pageable pageable);

    @Query(value = "select x from XeVanChuyen x where (0 = x.trangThai) and x.xoa = false")
    Page<XeVanChuyen> findByTrangThai( Pageable pageable);

    @Query(value = "from XeVanChuyen x " +
            "where " +
            " (0 = ?1 or x.doanhNghiep.idDoanhNghiep = ?1 and x.doanhNghiep.xoa = false) " +
            "and x.bienSoXe like concat('%',?2,'%') " +
            "and x.xoa = false " +
            "order by x.idXeVanChuyen asc ")
    Page<XeVanChuyen> findByBienSoXeAnddAndDoanhNghiep( int idDoanhNghiep, String  bienSoXe, Pageable pageable);

    @Query(value = "from XeVanChuyen x " +
            "where 0 = x.trangThai and " +
            " ( 0=?1 or x.doanhNghiep.idDoanhNghiep = ?1 ) " +
            "and x.bienSoXe like concat('%',?2,'%') " +
            "order by x.idXeVanChuyen asc ")
    Page<XeVanChuyen> findByTrangThaiAndDoanhNghiepAndBienSoXe(int doanhNghiepId, String bienSoXe, Pageable pageable);
}
