package com.tavi.tavi_mrs.repository.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.ThuTuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ThuTucRepo extends JpaRepository<ThuTuc, Integer> {

    @Modifying
    @Transactional
    @Query("update ThuTuc t set t.xoa = true where t.idThuTuc = ?1")
    int deleteByIdThuTuc(int thuTucId);

    @Query(value = "select t from ThuTuc t where t.xoa =?1 and t.idThuTuc = ?2")
    Optional<ThuTuc> findByIdThuTuc(int thuTucId, boolean xoa);

    @Query(value = "select t from ThuTuc t where t.xoa = false and t.doanhNghiep.idDoanhNghiep = ?1")
    Page<ThuTuc> findAllByDoanhNghiep(int doanhNghiepId, Pageable pageable);

}
