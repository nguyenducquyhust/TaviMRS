package com.tavi.tavi_mrs.repository.nguoi_dung;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
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
public interface NguoiDungRepo extends JpaRepository<NguoiDung, Integer> {

    Optional<NguoiDung> findByIdNguoiDungAndXoa(int nguoiDungId, boolean xoa);

    @Query(value = "select n from NguoiDung n where n.xoa = false")
    Page<NguoiDung> findAll(Pageable pageable);

    @Query(value = "select n from NguoiDung n where n.xoa = false")
    List<NguoiDung> findAll();

    @Query(value = "select n from NguoiDung n where (0 = ?1 or n.mo.idMo = ?1) and n.xoa = false")
    Page<NguoiDung> findByMoId(int moId, Pageable pageable);

    @Query(value = "select n from NguoiDung n where (0 = ?1 or n.doanhNghiep.idDoanhNghiep = ?1) and n.xoa = false")
    Page<NguoiDung> findByDoanhNghiepId(int doanhNghiepId, Pageable pageable);

    @Query(value = "from NguoiDung n " +
            "where " +
            "(0 = ?1 or n.mo.idMo = ?1 and n.mo.xoa = false)"+
            "and n.chucVu like concat('%', ?2, '%') "+
            "and n.tenDangNhap like concat('%', ?3, '%') "+
            "and n.soDienThoai like concat('%', ?4, '%') "+
            "and (0 = ?5 or n.trangThai = ?5) "+
            "and n.xoa = false "+
            "order by n.idNguoiDung asc ")
    Page<NguoiDung> findByMoIdAndTenDangNhapAndSoDienThoaiAndChucVuAndTrangThai(int moId, String tenDangNhap, String soDienThoai, String chucVu, int trangThai, Pageable pageable);

    @Query(value = "from NguoiDung n " +
            "where " +
            "n.tenDangNhap like concat('%', ?1, '%') " +
            "and (0 = ?2 or n.doanhNghiep.idDoanhNghiep = ?2 and n.doanhNghiep.xoa = false )  " +
            "and (0 = ?3 or n.trangThai = ?3  )  " +
            "and n.xoa = false " +
            "order by n.idNguoiDung asc ")
    Page<NguoiDung> findByTaiKhoanAndDoanhNghiepAndTrangThai(String tenDangNhap, int idDoanhNghiep, int  trangThai, Pageable pageable);

    @Query(value = "select n from NguoiDung n where n.xoa = false and n.email in (?1)")
    List<NguoiDung> findByEmailList(List<String> emailList);

    @Query(value = "select n from NguoiDung n where n.xoa = false and n.idNguoiDung in (?1)")
    List<NguoiDung> findByIdList(List<Integer> listId);

    @Transactional
    @Modifying
    @Query("update NguoiDung n set n.trangThai = (?1) where n.doanhNghiep.idDoanhNghiep in (?2)")
    int updateTrangThaiByDoanhNghiep(int trangThai, int doanhNghiepId);
}
