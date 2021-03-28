package com.tavi.tavi_mrs.repository.mo;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.mo.NhatKyKhaiThac;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTruLuongKhaiThac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface NhatKyKhaiThacRepo extends JpaRepository<NhatKyKhaiThac , Integer> {

    Optional<NhatKyKhaiThac> findByIdNhatKyKhaiThac(int idNhatKyKhaiThac);

    @Query("from NhatKyKhaiThac n " +
            "where " +
            "(0 = ?1 or n.mo.idMo = ?1) " +
            "and n.ngay >= ?2 and n.ngay <= ?3"+
            "order by n.idNhatKyKhaiThac ")
    Page<NhatKyKhaiThac> findByMoAndNgay(int moId, Date thoiGianTu, Date thoiGianDen, Pageable pageable);

    @Query(value = "select new com.tavi.tavi_mrs.entities.bieu_do.BieuDo(n.mo.doanhNghiep, sum(n.truLuongKhaiThac)) from NhatKyKhaiThac n " +
            "where n.ngay >= ?1 and n.ngay <= ?2 " +
            "group by n.mo.doanhNghiep " +
            "order by sum(n.truLuongKhaiThac) desc ")
    List<BieuDo> abstractBieuDoByDoanhNghiep(Date ngayDau, Date ngayCuoi);

    @Query(value = "select new com.tavi.tavi_mrs.entities.bieu_do.BieuDo(n.mo.khoangSan, sum(n.truLuongKhaiThac)) from NhatKyKhaiThac n " +
            "where n.ngay >= ?1 and n.ngay <= ?2 " +
            "group by n.mo.khoangSan " +
            "order by sum(n.truLuongKhaiThac) desc ")
    List<BieuDo> abstractBieuDoByKhoangSan(Date ngayDau, Date ngayCuoi);

    @Query(value = "select new com.tavi.tavi_mrs.entities.thong_ke.ThongKeTruLuongKhaiThac(n.mo, sum(n.truLuongKhaiThac)) from NhatKyKhaiThac n " +
            "where n.ngay >= ?1 and n.ngay <= ?2 " +
            "and (?3 = true or n.mo.khoangSan.idKhoangSan in (?4))" +
            "and (0 = ?5 or n.mo.huyenId = ?5) " +
            "group by n.mo")
    List<ThongKeTruLuongKhaiThac> abstractThongKeTruLuongKhaiThac(Date ngayDau, Date ngayCuoi, boolean allKhoangSan, List<Integer> khoangSanIds, Integer huyenId);
}
