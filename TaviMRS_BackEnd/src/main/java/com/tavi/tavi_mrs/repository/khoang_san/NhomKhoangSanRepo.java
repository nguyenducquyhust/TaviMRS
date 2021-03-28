package com.tavi.tavi_mrs.repository.khoang_san;

import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import com.tavi.tavi_mrs.entities.khoang_san.NhomKhoangSan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NhomKhoangSanRepo extends JpaRepository<NhomKhoangSan, Integer> {

    Optional<NhomKhoangSan> findByIdNhomKhoangSanAndXoa(int idNhomKhoangSan, boolean xoa);

    @Query(value = "from NhomKhoangSan n where n.tenNhomKhoangSan like concat('%', ?1, '%') and n.xoa = false order by n.idNhomKhoangSan asc ")
    Page<NhomKhoangSan> findByTen(String ten, Pageable pageable);

    List<NhomKhoangSan> findAllByXoa(Boolean xoa);

}
