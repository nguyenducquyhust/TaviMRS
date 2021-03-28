package com.tavi.tavi_mrs.repository.khoang_san;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface KhoangSanRepo extends JpaRepository<KhoangSan, Integer> {

    Optional<KhoangSan> findByIdKhoangSanAndXoa(int idKhoangSan, boolean xoa);

    @Query(value = "from KhoangSan k " +
            "where " +
            "k.tenKhoangSan like concat('%',?1,'%') " +
            "and (0 = ?2 or nhomKhoangSan.idNhomKhoangSan = ?2)" +
            "and xoa = false " +
            "order by idKhoangSan")
    Page<KhoangSan> findByTenAndNhomKhoangSanId(String ten, int idNhomKhoangSan, Pageable pageable);

    @Query(value = "from KhoangSan k where k.xoa = false order by k.idKhoangSan asc ")
    Page<KhoangSan> findAllToPage(Pageable pageable);

    @Query(value = "from KhoangSan k where k.xoa = false")
    List<KhoangSan> findAll();

    @Query(value = "from KhoangSan k " +
            "where " +
            "(0 = ?1 or k.nhomKhoangSan.idNhomKhoangSan = ?1)" +
            "and k.xoa = false " +
            "order by k.idKhoangSan")
    List<KhoangSan> findByNhomKhoangSan(int nhomKhoangSanId);
}
