package com.tavi.tavi_mrs.service.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GiayPhepKhaiThacService {

    Optional<GiayPhepKhaiThac> findByIdGiayPhepAndXoa(int giayPhepId, boolean xoa);

    Optional<GiayPhepKhaiThac> findByIdGiayPhepAndTrangThaiAndXoa(int idGiayPhep, int trangThai, boolean xoa);

    Page<GiayPhepKhaiThac> findByMoAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(int idMo, int idLoaiGiayPhep, int trangThai, int thamQuyenCap, Pageable pageable);

    Page<GiayPhepKhaiThac> findByDoanhNghiepAndLoaiGiayPhepAndTrangThaiAndThamQuyenCap(int idDoanhNghiep, int idLoaiGiayPhep, int trangThai, int thamQuyenCap, Pageable pageable);

    Map<Integer, List<GiayPhepKhaiThac>> abstractReport(Date ngauDau, Date ngayCuoi, List<Integer> khoangSanIds, int huyenId);

    Optional<GiayPhepKhaiThac> save(GiayPhepKhaiThac giayPhepKhaiThac);

    boolean deleteById(int idGiayPhepKhaiThac);

    Page<GiayPhepKhaiThac> findAllToPage(Pageable pageable);

    Page<GiayPhepKhaiThac> findAll(Pageable pageable);

    Page<GiayPhepKhaiThac> findByIdMoAndLoaiGiayPhepIdAndTrangThaiAndThamQuyenCapAndSoQuyetDinh(int idMo, int loaiGiayPhepId, int trangThai, int thamQuyenCap, String soQuyetDinh, Pageable pageable);
}
