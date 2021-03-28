package com.tavi.tavi_mrs.service.nguoi_dung;

import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NguoiDungService {

    Page<NguoiDung> findByMoId(int moId, Pageable pageable);

    Page<NguoiDung> findAll(Pageable pageable);

    List<NguoiDung> findAll();

    Optional<NguoiDung> findByIdNguoiDungAndXoa(int nguoiDungId, boolean xoa);

    Optional<NguoiDung> save(NguoiDung nguoiDung);

    Page<NguoiDung> findByDoanhNghiepId(int doanhNghiepId,Pageable pageable);

    Page<NguoiDung> findByTaiKhoanAndDoanhNghiepAndTrangThai(String tenDangNhap, int idDoanhNghiep, int  trangThai, Pageable pageable);

    Page<NguoiDung> findByMoIdAndTenDangNhapAndSoDienThoaiAndChucVuAndTrangThai(int moId, String tenDangNhap, String soDienThoai, String chucVu, int trangThai, Pageable pageable);
}
