package com.tavi.tavi_mrs.repository.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhViPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HinhAnhViPhamRepo extends JpaRepository<HinhAnhViPham, Integer> {

    Optional<HinhAnhViPham> findByIdHinhAnhViPham(int idHinhAnhViPham);

    @Query(value = "from HinhAnhViPham h " +
            "where " +
            "(0 = ?1 or h.viPham.idViPham = ?1 and h.viPham.xoa = false) " +
            "and (?2 is null or h.thoiGianTao >= ?2 ) and (?3 is null or h.thoiGianTao <= ?3)" +
            "order by h.thoiGianTao asc ")
    Page<HinhAnhViPham> findByViPhamAndThoiGian(int idViPham, Date ngayDau, Date ngayCuoi, Pageable pageable);

    @Query(value = "from HinhAnhViPham h where (0 = ?1 or h.viPham.idViPham = ?1 and h.viPham.xoa = false) order by h.idHinhAnhViPham asc ")
    Page<HinhAnhViPham> findByViPhamId(int idViPham, Pageable pageable);

    @Query(value = "from HinhAnhViPham h where (0 = ?1 or h.viPham.idViPham = ?1 and h.viPham.xoa = false) order by h.idHinhAnhViPham asc ")
    List<HinhAnhViPham> findByViPhamAll(int idViPham);

    //find all sort time
    @Query(value = "from HinhAnhViPham h where h.hinhAnhLuuTru.xoa = false order by h.hinhAnhLuuTru.thoiGian asc ")
    Page<HinhAnhViPham> findAllOrOrderByThoiGianAsc(Pageable pageable);

    @Query(value = "from HinhAnhViPham h where h.hinhAnhLuuTru.xoa = false order by h.hinhAnhLuuTru.thoiGian desc")
    Page<HinhAnhViPham> findAllOrOrderByThoiGianDesc(Pageable pageable);
    //end find all sort time

    //search sort doanh nghiep time
    @Query(value = "from HinhAnhViPham h " +
            "where " +
            "(0 = ?1 or h.hinhAnhLuuTru.mo.doanhNghiep.idDoanhNghiep = ?1 and h.hinhAnhLuuTru.xoa = false and h.hinhAnhLuuTru.mo.xoa = false and h.hinhAnhLuuTru.mo.doanhNghiep.xoa = false) " +
            "and (0 = ?2 or h.viPham.loaiViPham.idLoaiViPham = ?2 and h.viPham.xoa = false and h.viPham.loaiViPham.xoa = false )" +
            "and (?3 is null or h.hinhAnhLuuTru.thoiGian >= ?3 ) and (?4 is null or h.hinhAnhLuuTru.thoiGian <= ?4)"+
            "and h.hinhAnhLuuTru.xoa = false " +
            "order by h.hinhAnhLuuTru.thoiGian asc ")
    Page<HinhAnhViPham> findByDoanhNghiepAndLoaiViPhamAndThoiGianOrderByThoiGianAsc(int idDoanhNghiep, int idLoaiViPham, Date ngayDau, Date ngayCuoi, Pageable pageable);

    @Query(value = "from HinhAnhViPham h " +
            "where " +
            "(0 = ?1 or h.hinhAnhLuuTru.mo.doanhNghiep.idDoanhNghiep = ?1 and h.hinhAnhLuuTru.xoa = false and h.hinhAnhLuuTru.mo.xoa = false and h.hinhAnhLuuTru.mo.doanhNghiep.xoa = false) " +
            "and (0 = ?2 or h.viPham.loaiViPham.idLoaiViPham = ?2 and h.viPham.xoa = false and h.viPham.loaiViPham.xoa = false )" +
            "and (?3 is null or h.hinhAnhLuuTru.thoiGian >= ?3 ) and (?4 is null or h.hinhAnhLuuTru.thoiGian <= ?4)"+
            "and h.hinhAnhLuuTru.xoa = false " +
            "order by h.hinhAnhLuuTru.thoiGian desc ")
    Page<HinhAnhViPham> findByDoanhNghiepAndLoaiViPhamAndThoiGianOrderByThoiGianDesc(int idDoanhNghiep, int idLoaiViPham, Date ngayDau, Date ngayCuoi, Pageable pageable);
    //end search sort doanh nghiep time

//    @Query(value = "from HinhAnhViPham h where (0 = ?1 or h.doanhNghiep.idDoanhNghiep = ?1 and h.doanhNghiep.xoa = false) and h.xoa = false order by h.idHinhAnhViPham asc ")
//    Page<HinhAnhViPham> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable);
}
