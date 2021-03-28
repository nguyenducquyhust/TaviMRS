package com.tavi.tavi_mrs.service.mo;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.mo.NhatKyKhaiThac;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTruLuongKhaiThac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NhatKyKhaiThacService {

    Optional<NhatKyKhaiThac> findByIdNhatKyKhaiThac(int idNhatKyKhaiThac);

    Page<NhatKyKhaiThac> findByMoAndNgay(int moId, Date thoiGianTu, Date thoiGianDen , Pageable pageable);

    List<BieuDo> abstractBieuDoByDoanhNghiep(Date ngayDau, Date ngayCuoi);

    List<BieuDo> abstractBieuDoByKhoangSan(Date ngayDau, Date ngayCuoi);

    List<ThongKeTruLuongKhaiThac> abstractThongKeTruLuongKhaiThac(Date ngayDau, Date ngayCuoi, List<Integer> khoangSanIds, Integer huyenId);
}
