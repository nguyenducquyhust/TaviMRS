package com.tavi.tavi_mrs.repository.bao_cao;

import com.tavi.tavi_mrs.entities.bao_cao.BaoCao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BaoCaoRepo extends JpaRepository<BaoCao, Integer> {

    Optional<BaoCao> findByIdBaoCaoAndXoa(int idBaoCao, boolean xoa);

    @Query(value = "from BaoCao b where b.xoa = false order by b.thoiGianTao asc ")
    Page<BaoCao> findAllToPage(Pageable pageable);

    @Query(value = "from BaoCao b where (0 = ?1 or b.thongBao.idThongBao = ?1 and b.thongBao.xoa = false) and b.xoa = false order by b.thoiGianTao asc ")
    Page<BaoCao> findByThongBao(int idThongBao, Pageable pageable);

    @Query(value = "from BaoCao b " +
            "where " +
            "(b.tieuDe is null or b.tieuDe like concat('%',?1,'%') )" +
            "and (?2 is null or b.thoiGianGui >= ?2 ) and (?3 is null or b.thoiGianGui <= ?3)" +
            "and b.xoa = ?4 " +
            "order by b.thoiGianTao ")
    Page<BaoCao> findByTieuDeAndThoiGian(String tieuDe, Date ngayDau, Date ngayCuoi, Pageable pageable);

    @Query(value = "from BaoCao b " +
            "where " +
            "(b.tieuDe is null or b.tieuDe like concat('%',?1,'%') )" +
            "and (b.nguoiDung.doanhNghiep.tenDoanhNghiep is null or b.nguoiDung.doanhNghiep.tenDoanhNghiep like concat('%',?2,'%') )"+
            "and (?3 is null or b.thoiGianGui >= ?3 ) and (?4 is null or b.thoiGianGui <= ?4)" +
            "and b.xoa = false " +
            "order by b.thoiGianGui ")
    Page<BaoCao> findByTieuDeAndDoanhNghiepAndThoiGian(String tieuDe, String doanhNghiep, Date ngayDau, Date ngayCuoi, Pageable pageable);
}
