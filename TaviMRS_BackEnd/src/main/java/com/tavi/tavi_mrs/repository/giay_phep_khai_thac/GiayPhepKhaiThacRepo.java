package com.tavi.tavi_mrs.repository.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GiayPhepKhaiThacRepo extends JpaRepository<GiayPhepKhaiThac, Integer> {

    Optional<GiayPhepKhaiThac> findByIdGiayPhepAndXoa(int idGiayPhep, boolean xoa);

    @Query(value = "select g from GiayPhepKhaiThac g where g.xoa = false")
    Page<GiayPhepKhaiThac> findAll(Pageable pageable);

    Optional<GiayPhepKhaiThac> findByIdGiayPhepAndTrangThaiAndXoa(int idGiayPhep, int trangThai, boolean xoa);

    @Query(value = "from GiayPhepKhaiThac g " +
            "where " +
            "(0 = ?1 or g.mo.idMo = ?1 and g.mo.xoa = false) " +
            "and (0 = ?2 or g.loaiGiayPhep.idLoaiGiayPhep = ?2 and g.loaiGiayPhep.xoa = false) " +
            "and (g.trangThai = ?3 or 0 = ?3) " +
            "and (g.thamQuyenCap = ?4 or -1 = ?4) " +
            "and g.xoa = false " +
            "order by g.idGiayPhep asc ")
    Page<GiayPhepKhaiThac> findByMoAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(int idMo, int idLoaiGiayPhep, int trangThai, int thamQuyenCap, Pageable pageable);

    @Query(value = "select g from GiayPhepKhaiThac g where g.mo.idMo = ?1 and (g.xoa = ?2 or ?2 is null)")
    Page<GiayPhepKhaiThac> findAllByMoIdAndXoa(int moId, boolean xoa, Pageable pageable);

    @Query(value = "from GiayPhepKhaiThac g " +
            "where " +
            "(0 = ?1 or g.doanhNghiep.idDoanhNghiep = ?1 and g.doanhNghiep.xoa = false) " +
            "and (0 = ?2 or g.loaiGiayPhep.idLoaiGiayPhep = ?2 and g.loaiGiayPhep.xoa = false) " +
            "and (g.trangThai = ?3 or 0 = ?3) " +
            "and (g.thamQuyenCap = ?4 or -1 = ?4) " +
            "and g.xoa = false " +
            "order by g.idGiayPhep asc ")
    Page<GiayPhepKhaiThac> findByDoanhNghiepAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(int idDoanhNghiep, int idLoaiGiayPhep, int trangThai, int thamQuyenCap, Pageable pageable);

    @Query("select g from GiayPhepKhaiThac g " +
            "where g.xoa = false " +
            "and (?1 is null or g.ngayCap >= ?1) " +
            "and (?2 is null or g.ngayCap <= ?2) " +
            "and (0 = ?5 or g.mo.huyenId = ?5)" +
            "and (?3 = true or g.mo.khoangSan.idKhoangSan in ?4) " +
            "order by g.thamQuyenCap, g.doanhNghiep")
    List<GiayPhepKhaiThac> abstractReport(Date ngauDau, Date ngayCuoi, boolean allKhoangSan, List<Integer> khoangSanIds, int huyenId);

    @Query(value = "from GiayPhepKhaiThac g where g.xoa = false order by g.idGiayPhep asc ")
    Page<GiayPhepKhaiThac> findAllToPage(Pageable pageable);

    @Query(value = "from GiayPhepKhaiThac g " +
            "where " +
            "(0 = ?1 or g.mo.idMo = ?1) " +
            "and (0 = ?2 or g.loaiGiayPhep.idLoaiGiayPhep = ?2) " +
            "and (-1 = ?3 or g.trangThai = ?3) " +
            "and (-1 = ?4 or g.thamQuyenCap = ?4) " +
            "and g.soQuyetDinh like concat('%', ?5, '%')" +
            "order by g.idGiayPhep asc ")
    Page<GiayPhepKhaiThac> findByIdMoAndLoaiGiayPhepIdAndTrangThaiAndThamQuyenCapAndSoQuyetDinh(int idMo, int loaiGiayPhepId, int trangThai, int thamQuyenCap, String soQuyetDinh, Pageable pageable);
}
