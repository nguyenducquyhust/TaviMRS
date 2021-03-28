package com.tavi.tavi_mrs.repository.hinh_anh;

import com.tavi.tavi_mrs.entities.bao_cao.BaoCao;
import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface HinhAnhLuuTruRepo extends JpaRepository<HinhAnhLuuTru, Integer> {

    Optional<HinhAnhLuuTru> findByIdHinhAnhAndXoa(int idHinhAnh, boolean xoa);

    @Query(value = "from HinhAnhLuuTru h where h.xoa = false order by h.idHinhAnh asc ")
    Page<HinhAnhLuuTru> findAllToPage(Pageable pageable);

    //find all sort time
    @Query(value = "from HinhAnhLuuTru h where h.xoa = false order by h.thoiGian asc ")
    Page<HinhAnhLuuTru> findAllByOrOrderByThoiGianAsc(Pageable pageable);

    @Query(value = "from HinhAnhLuuTru h where h.xoa = false order by h.thoiGian desc")
    Page<HinhAnhLuuTru> findAllByOrOrderByThoiGianDesc(Pageable pageable);
    //end find all sort time

    @Query(value = "from HinhAnhLuuTru h " +
            "where " +
            "(0 = ?1 or h.mo.idMo = ?1 and h.mo.xoa = false) " +
            "and (?2 is null or h.thoiGianTao >= ?2 ) and (?3 is null or h.thoiGianTao <= ?3)"+
            "and h.xoa = false " +
            "order by h.thoiGianTao asc ")
    Page<HinhAnhLuuTru> findByMoIdAndThoiGian(int idMo, Date ngayDau, Date ngayCuoi, Pageable pageable);

    //search sort doanh nghiep time
    @Query(value = "from HinhAnhLuuTru h " +
            "where " +
            "(0 = ?1 or h.mo.doanhNghiep.idDoanhNghiep = ?1 and h.mo.xoa = false and h.mo.doanhNghiep.xoa = false) " +
            "and (?2 is null or h.thoiGian >= ?2 ) and (?3 is null or h.thoiGian <= ?3)"+
            "and h.xoa = false " +
            "order by h.thoiGian asc ")
    Page<HinhAnhLuuTru> findByDoanhNghiepIdAndThoiGianOrderByThoiGianAsc(int idDoanhNghiep, Date ngayDau, Date ngayCuoi, Pageable pageable);

    @Query(value = "from HinhAnhLuuTru h " +
            "where " +
            "(0 = ?1 or h.mo.doanhNghiep.idDoanhNghiep = ?1 and h.mo.xoa = false and h.mo.doanhNghiep.xoa = false) " +
            "and (?2 is null or h.thoiGian >= ?2 ) and (?3 is null or h.thoiGian <= ?3)"+
            "and h.xoa = false " +
            "order by h.thoiGian desc ")
    Page<HinhAnhLuuTru> findByDoanhNghiepIdAndThoiGianOrderByThoiGianDesc(int idDoanhNghiep, Date ngayDau, Date ngayCuoi, Pageable pageable);
    //end search sort doanh nghiep time

    @Query(value = "from HinhAnhLuuTru h where (0 = ?1 or h.mo.idMo = ?1 and h.mo.xoa = false) and h.xoa = false order by h.idHinhAnh asc ")
    Page<HinhAnhLuuTru> findByMoId(int idMo, Pageable pageable);
}
