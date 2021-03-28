package com.tavi.tavi_mrs.service.khoang_san;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KhoangSanService {

    Optional<KhoangSan> findByIdKhoangSanAndXoa(Integer idKhoangSan, boolean xoa);

    List<KhoangSan> findAll();

    Page<KhoangSan> findByTenAndNhomKhoangSanId(String ten, int nhomKhoangSanId, Pageable pageable);

    Page<KhoangSan> findAllToPage(Pageable pageable);

    List<KhoangSan> findByNhomKhoangSan(int nhomKhoangSanId);

    Optional<KhoangSan> save(KhoangSan khoangSan);
}
