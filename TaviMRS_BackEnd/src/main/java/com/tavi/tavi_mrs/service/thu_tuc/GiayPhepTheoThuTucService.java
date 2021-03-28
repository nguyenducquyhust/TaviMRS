package com.tavi.tavi_mrs.service.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoThuTuc;

import java.util.List;
import java.util.Optional;

public interface GiayPhepTheoThuTucService {

    Optional<GiayPhepTheoThuTuc> findByIdGiayPhepTheoThuTuc(int idGiayPhepTheoThuTuc);

    List<GiayPhepTheoThuTuc> findByThuTuc(int thuTucId);
}
