package com.tavi.tavi_mrs.repository.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTheoLuongXeVaoMo;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.NhatKyXeVaoMo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface NhatKyXeVaoMoRepo extends JpaRepository<NhatKyXeVaoMo, Integer> {

    Optional<NhatKyXeVaoMo> findByIdNhatKyXeVaoMo(int idNhatKyXeVaoMo);

    @Query(value = "from NhatKyXeVaoMo n where (0 = ?1 or n.mo.idMo = ?1 and n.mo.xoa = false)  order by n.thoiGianTao asc ")
    Page<NhatKyXeVaoMo> findByMo(int idMo, Pageable pageable);


    @Query(value = "from NhatKyXeVaoMo n " +
            "where " +
            "(0 = ?1 or n.mo.idMo = ?1 and n.mo.xoa = false) " +
            "and (?2 is null or n.thoiGianVao >= ?2 ) and (?3 is null or n.thoiGianRa <= ?3)" +
            "order by n.thoiGianTao asc ")
    Page<NhatKyXeVaoMo> findByMoAndThoiGian(int idMo, Date ngayDau, Date ngayCuoi, Pageable pageable);

    @Query(value = "from NhatKyXeVaoMo n " +
            "where " +
            "(0 = ?1 or n.mo.idMo = ?1 and n.mo.xoa = false) " +
            "and (0 = ?2 or n.xeVanChuyen.idXeVanChuyen = ?2 and n.xeVanChuyen.xoa = false) " +
            "and (?3 is null or n.thoiGianVao >= ?3 ) and (?4 is null or n.thoiGianRa <= ?4)" +
            "order by n.thoiGianTao asc ")
    List<NhatKyXeVaoMo> findByMoAndIdXeAndThoiGian(int idMo,int idXeVanChuyen, Date ngayDau, Date ngayCuoi);


    @Query(value = "from NhatKyXeVaoMo n " +
            "where " +
            "(0 = ?1 or n.mo.doanhNghiep.idDoanhNghiep = ?1 and n.mo.doanhNghiep.xoa = false) " +
            "and (?2 is null or n.thoiGianVao >= ?2 ) and (?3 is null or n.thoiGianRa <= ?3)" +
            "order by n.thoiGianTao asc ")
    List<NhatKyXeVaoMo> findByDoanhNghiepAndThoiGian(int idDoanhNghiep, Date ngayDau, Date ngayCuoi);

    @Query(value = "SELECT SUM(trong_luong_khoang_san) as tong, MONTH(thoi_gian_ra) as x\n" +
            "FROM TaviMineralResources_DEV.dbo.Nhat_Ky_Xe_Vao_Mo \n" +
            "WHERE xe_van_chuyen_id = ?1\n" +
            "GROUP BY MONTH(thoi_gian_ra);", nativeQuery = true)
    List<Map<String, Object>> findTongKhaiThac(int xeVanChuyenId);

    @Query(value = "select count(n) from NhatKyXeVaoMo n where n.mo.idMo = ?1 and n.thoiGianVao = ?2")
    int countByMoAndNgay(int idMo, Date ngay);

    @Query(value = "select new com.tavi.tavi_mrs.entities.thong_ke.ThongKeTheoLuongXeVaoMo(n.xeVanChuyen, n.mo, count(n), sum(n.trongLuongKhoangSan)) from NhatKyXeVaoMo n " +
            "where (?1 is null or n.thoiGianVao >= ?1 ) and (?2 is null or n.thoiGianVao <= ?2) " +
            "and (0 = ?3 or n.mo.doanhNghiep.idDoanhNghiep = ?3) " +
            "group by n.xeVanChuyen, n.mo")
    List<ThongKeTheoLuongXeVaoMo> abstractThongKeTheoLuongXeVaoMo(Date ngayDau, Date ngayCuoi, Integer doanhNghiepId);

    //named query
    @Query(nativeQuery = true)
    List<BieuDo> abstractBieuDoByKhoangSan(int khoangSanId);

    @Query(nativeQuery = true)
    List<BieuDo> abstractBieuDoByDoanhNghiep(int khoangSanId);
}
