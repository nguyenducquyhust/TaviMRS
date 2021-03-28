package com.tavi.tavi_mrs.repository.thong_bao;

import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ThongBaoRepo extends JpaRepository<ThongBao, Integer> {

    Optional<ThongBao> findByIdThongBaoAndXoa(int idThongBao, boolean xoa);

    @Modifying
    @Transactional
    @Query("update ThongBao g set g.xoa = true where g.idThongBao = ?1")
    int deleteByIdThongBao(int idThongBao);

    Page<ThongBao> findAll(Pageable pageable);

    @Query("from ThongBao t " +
            "where " +
            "(?1 is null or t.thoiGianGui >= ?1 ) and (?2 is null or t.thoiGianGui <= ?2)"+
            "and t.tieuDe like concat('%',?3,'%')"+
            "and t.xoa = false " +
            "order by t.thoiGianGui")
    Page<ThongBao> findByThoiGianGuiAndTieuDe(Date ngayDau , Date ngayCuoi , String tieuDe  , Pageable pageable);

}
