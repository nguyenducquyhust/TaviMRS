package com.tavi.tavi_mrs.service.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.LoaiGiayPhep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LoaiGiayPhepService {

    Optional<LoaiGiayPhep> findByIdLoaiGiayPhepAndXoa(int idLoaiGiayPhep, boolean xoa);

    List<LoaiGiayPhep> findAllByXoa(Boolean xoa);

    Optional<LoaiGiayPhep> save(LoaiGiayPhep loaiGiayPhep);

    boolean deleteById(int idLoaiGiayPhep);

    Page<LoaiGiayPhep> findAll(Pageable pageable);
}
