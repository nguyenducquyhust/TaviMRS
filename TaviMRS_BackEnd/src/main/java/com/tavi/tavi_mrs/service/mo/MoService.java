package com.tavi.tavi_mrs.service.mo;

import com.tavi.tavi_mrs.entities.common.TrangThaiKetNoi;
import com.tavi.tavi_mrs.entities.mo.Mo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MoService {

    Optional<Mo> findByIdMoAndXoa(int idMo, boolean xoa);

    Page<Mo> findAllToPage(Pageable pageable);

    Page<Mo> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable);

    List<Mo> findByTrangThaiHoatDong(int trangThaiHoatDong);

    Page<Mo> findPageByKhoangSanIdsAndThongTinDoanhNghiep(List<Integer> khoangSanIds, String name, Integer huyenId, Pageable pageable);

    List<Mo> findListByKhoangSanIds(List<Integer> khoangSanIds);

    Page<Mo> findByKhoangSanIdsAndDoanhNghiepIdAndDiaChiAndTinhIdAndHuyenId(List<Integer> khoangSanIds, int idDoanhNghiep, String diaChi, int tinhId, int huyenId, Pageable pageable);

    Optional<Mo> save(Mo mo);

    Boolean updateTrangThaiKetNoiByIdMo(Integer moId, int trangThaiKetNoi);
}
