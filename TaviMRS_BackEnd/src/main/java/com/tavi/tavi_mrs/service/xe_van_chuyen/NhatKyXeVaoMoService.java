package com.tavi.tavi_mrs.service.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.thong_ke.ThongKeTheoLuongXeVaoMo;
import com.tavi.tavi_mrs.entities.xe_van_chuyen.NhatKyXeVaoMo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NhatKyXeVaoMoService {

    Optional<NhatKyXeVaoMo> findByIdNhatKyXeVaoMo(int idNhatKyXeVaoMo);

    Page<NhatKyXeVaoMo> findByMo(int idMo, Pageable pageable);

    Page<NhatKyXeVaoMo> findByMoAndThoiGian(int idMo, Date ngayDau, Date ngayCuoi, Pageable pageable);

    List<NhatKyXeVaoMo> findByMoAndIdXeAndThoiGian(int idMo,int idXeVanChuyen, Date ngayDau, Date ngayCuoi);

    List<NhatKyXeVaoMo> findByDoanhNghiepAndThoiGian(int idDoanhNghiep, Date ngayDau, Date ngayCuoi);

    List<ThongKeTheoLuongXeVaoMo> abstractThongKeTheoLuongXeVaoMo(Date ngayDau, Date ngayCuoi, Integer doanhNghiepId);

    List<Map<String, Object>> findTongKhaiThac(int xeVanChuyenId);

    int countByMoAndNgay(int idMo, Date ngay);
}
