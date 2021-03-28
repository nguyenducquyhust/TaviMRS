package com.tavi.tavi_mrs.service.doanh_nghiep;

import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DoanhNghiepService {

    Optional<DoanhNghiep> findByIdDoanhNghiepAndXoa(int idDoanhNghiep, boolean xoa);

    Page<DoanhNghiep> findAll(Pageable pageable);

    Optional<DoanhNghiep> save(DoanhNghiep doanhNghiep);

    Optional<DoanhNghiep> saveCauHinh(DoanhNghiep doanhNghiep);

    Page<DoanhNghiep> findByTenAndDiaChiAndTrangThaiPhanMem(String tenDoanhNghiep, String diaChi, int trangThaiPhanMem, Pageable pageable);
}
