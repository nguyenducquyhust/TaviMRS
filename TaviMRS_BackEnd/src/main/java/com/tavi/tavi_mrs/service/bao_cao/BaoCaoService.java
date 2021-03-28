package com.tavi.tavi_mrs.service.bao_cao;

import com.tavi.tavi_mrs.entities.bao_cao.BaoCao;
import com.tavi.tavi_mrs.entities.hinh_anh.HinhAnhLuuTru;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface BaoCaoService{

    Optional<BaoCao> findByIdBaoCaoAndXoa(int idBaoCao, boolean xoa);

    Page<BaoCao> findAllToPage(Pageable pageable);

    Page<BaoCao> findByThongBao(int idThongBao, Pageable pageable);

    Page<BaoCao> findByTieuDeAndThoiGian(String tieuDe, Date ngayDau, Date ngayCuoi, Pageable pageable);

    Page<BaoCao> findByTieuDeAndDoanhNghiepAndThoiGian(String tieuDe, String doanhNghiep, Date ngayDau, Date ngayCuoi, Pageable pageable);
}
