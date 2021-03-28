package com.tavi.tavi_mrs.service.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.XeVanChuyen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface XeVanChuyenService {

    Optional<XeVanChuyen> findByIdXeVanChuyenAndXoa(int idXeVanChuyen, boolean xoa);

    Page<XeVanChuyen> findAll(Pageable pageable);

    Page<XeVanChuyen> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable);

    Page<XeVanChuyen> findByTrangThai( Pageable pageable);

    Optional<XeVanChuyen> save(XeVanChuyen xeVanChuyen);

    Page<XeVanChuyen> findByDoanhNghiepIdAndBienSoXe(int doanhNghiepId, String bienSoXe, Pageable pageable);

    Page<XeVanChuyen> findByBienSoXeAndTrangThai(String bienSoXe, int trangThai, Pageable pageable);

    Page<XeVanChuyen> findByTrangThaiAndDoanhNghiepAndBienSoXe(int doanhNghiepId, String bienSoXe, Pageable pageable);

}
