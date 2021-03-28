package com.tavi.tavi_mrs.service.thiet_bi;

import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ThietBiService {

    Optional<ThietBi> findByIdThietBiAndXoa(int idThietBi, boolean xoa);

    Page<ThietBi> findAll(Pageable pageable);

    Page<ThietBi> findByMoAndTrangThaiAndTen(int idMo, int trangThai, String tenThietBi, Pageable pageable);

    Page<ThietBi> findByMoAndTrangThaiAndMaThieBi(int idMo, int trangThaiKetNoi, String  maThietBi, Pageable pageable);

    Page<ThietBi> findByMoIdAndDoanhNghiepIdAndTrangThaiKetNoiAndTrangThai(int idMo, int idDoanhNghiep, Boolean trangThaiKetNoi, int trangThai, Pageable pageable);

    Page<ThietBi> findByMoId(int idMo, Pageable pageable);

    Page<ThietBi> findByDoanhNghiepId(int idDoanhNghiep, Pageable pageable);

    boolean updateTrangThaiKetNoiById(Integer idThietBi, int trangThai);

    boolean updateTrangThaiKetNoiByMo(Integer moId, int trangThai);

    Optional<ThietBi> save(ThietBi thietBi);
}
