package com.tavi.tavi_mrs.repository.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.LoaiGiayPhep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoaiGiayPhepRepo extends JpaRepository<LoaiGiayPhep, Integer> {

    Optional<LoaiGiayPhep> findByIdLoaiGiayPhepAndXoa(int idLoaiGiayPhep, boolean xoa);

    @Query(value = "from LoaiGiayPhep l where l.xoa = ?1 or ?1 is null")
    List<LoaiGiayPhep> findAllByXoa(Boolean xoa);

    @Query(value = "select l from LoaiGiayPhep l where l.xoa = false")
    Page<LoaiGiayPhep> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("update GiayPhepKhaiThac g set g.xoa = true where g.idGiayPhep = ?1")
    int deleteByIdLoaiGiayPhep(int idLoaiGiayPhep);

}
