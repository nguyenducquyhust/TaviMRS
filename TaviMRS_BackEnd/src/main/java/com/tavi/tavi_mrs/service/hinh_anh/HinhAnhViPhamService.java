package com.tavi.tavi_mrs.service.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhViPham;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HinhAnhViPhamService {

    Optional<HinhAnhViPham> findByIdHinhAnhViPham(int idHinhAnhViPham);

    Page<HinhAnhViPham> findByViPhamAndThoiGian(int idViPham, Date ngayDau,Date ngayCuoi, Pageable pageable);

    Page<HinhAnhViPham> findByViPhamId(int idViPham, Pageable pageable);

    List<HinhAnhViPham> findByViPhamAll(int idViPham);

    Page<HinhAnhViPham> findAllOrderByThoiGian(Pageable pageable, boolean sort);

    Page<HinhAnhViPham> findByDoanhNghiepAndLoaiViPhamAndThoiGianOrderByThoiGian(int idDoanhNghiep, int idLoaiViPham, Date ngayDau, Date ngayCuoi, Pageable pageable, boolean sort);

//    Page<HinhAnhViPham> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable);

}
