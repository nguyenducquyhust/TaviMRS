package com.tavi.tavi_mrs.repository.doanh_nghiep;

import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoanhNghiepRepo extends JpaRepository<DoanhNghiep, Integer> {

    Optional<DoanhNghiep> findByIdDoanhNghiepAndXoa(int idDoanhNghiep, boolean xoa);

    @Query(value = "from DoanhNghiep d where d.idDoanhNghiep = ?1 and d.khoa = ?2 and d.xoa = false")
    Optional<DoanhNghiep> findByIdDoanhNghiepAndKhoa(int idDoanhNghiep, String khoa);

    @Query(value = "from DoanhNghiep d " +
            "where d.xoa = false " +
            "order by d.idDoanhNghiep, d.trangThaiHoatDong, d.trangThaiPhanMem")
    Page<DoanhNghiep> findAll(Pageable pageable);

    @Query(value = "from DoanhNghiep d " +
            "where " +
            "(d.tenDoanhNghiep like concat('%', ?1, '%')) " +
            "and d.diaChi like concat('%', ?2, '%') " +
            "and (-1 = ?3 or d.trangThaiPhanMem = ?3) " +
            "order by d.idDoanhNghiep asc ")
    Page<DoanhNghiep> findByTenAndDiaChiAndTrangThaiPhanMem(String tenDoanhNghiep, String diaChi, int trangThaiPhanMem, Pageable pageable);
}
