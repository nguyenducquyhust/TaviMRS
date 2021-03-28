package com.tavi.tavi_mrs.service.khoang_san;

import com.tavi.tavi_mrs.entities.khoang_san.NhomKhoangSan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NhomKhoangSanService {

    Optional<NhomKhoangSan> findByIdNhomKhoangSanAndXoa(int idNhomKhoangSan, boolean xoa);

    Page<NhomKhoangSan> findByTen(String ten, Pageable pageable);

    Optional<NhomKhoangSan> save(NhomKhoangSan nhomKhoangSan);

    List<NhomKhoangSan> findAllByXoa(Boolean xoa);
}
