package com.tavi.tavi_mrs.service.quyen;

import com.tavi.tavi_mrs.entities.quyen.NhomQuyen;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NhomQuyenService {
    Optional<NhomQuyen> findByIdNhomQuyen(int idNhomQuyen);

    Page<NhomQuyen> findAllToPage(Pageable pageable);

    Optional<NhomQuyen> save(NhomQuyen nhomQuyen);
}
