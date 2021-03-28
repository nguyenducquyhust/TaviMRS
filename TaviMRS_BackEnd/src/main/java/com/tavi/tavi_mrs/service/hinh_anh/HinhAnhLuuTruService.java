package com.tavi.tavi_mrs.service.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface HinhAnhLuuTruService {

    Optional<HinhAnhLuuTru> findByIdHinhAnhAndXoa(int idHinhAnh, boolean xoa);

    Page<HinhAnhLuuTru> findAllToPage(Pageable pageable);

    Page<HinhAnhLuuTru> findAllOrderByThoiGian(Pageable pageable, boolean sort);

    Page<HinhAnhLuuTru> findByDoanhNghiepIdAndThoiGianOrderByThoiGian(int idDoanhNghiep, Date ngayDau, Date ngayCuoi, Pageable pageable, boolean sort);

    Page<HinhAnhLuuTru> findByMoIdAndThoiGian(int idMo, Date ngayDau, Date ngayCuoi, Pageable pageable);

    Page<HinhAnhLuuTru> findByMoId(int idMo, Pageable pageable);
}
