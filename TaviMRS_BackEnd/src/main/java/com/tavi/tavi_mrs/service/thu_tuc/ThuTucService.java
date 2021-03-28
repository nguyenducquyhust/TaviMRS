package com.tavi.tavi_mrs.service.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.ThuTuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThuTucService {

    boolean deleteById(int giayPhepId);

    Optional<ThuTuc> findByIdThuTuc(int thuTucId, Boolean xoa);

    Optional<ThuTuc> save(ThuTuc thuTuc);

    Page<ThuTuc> findAllByDoanhNghiep(int doanhNghiepId, Pageable pageable);
}
