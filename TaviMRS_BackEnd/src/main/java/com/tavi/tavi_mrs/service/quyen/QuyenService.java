package com.tavi.tavi_mrs.service.quyen;

import com.tavi.tavi_mrs.entities.quyen.Quyen;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuyenService {
    Optional<Quyen> findByIdQuyen(int idQuyen);

    Page<Quyen> findAllToPage(Pageable pageable);

    Optional<Quyen> save(Quyen quyen);
}
