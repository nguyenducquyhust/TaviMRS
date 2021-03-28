package com.tavi.tavi_mrs.service.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoLoaiThuTuc;

import java.util.List;
import java.util.Optional;

public interface GiayPhepTheoLoaiThuTucService {

    Optional<GiayPhepTheoLoaiThuTuc> findByIdGiayPhepTheoLoaiThuTuc(int idGiayPhepTheoLoaiThuTuc);

    List<GiayPhepTheoLoaiThuTuc> findByLoaiThuTuc(int loaiThuTucId);
}
