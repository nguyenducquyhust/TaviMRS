package com.tavi.tavi_mrs.service.lich_su_ket_noi;

import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LichSuKetNoiService {

    Optional<LichSuKetNoi> findByIdLichSuKetNoiAndXoa(int idLichSuKetNoi , boolean xoa);

    List<LichSuKetNoi> findByTrangThaiXuLy(int trangThaiXuLy);

    Page<LichSuKetNoi> findByMo(Integer moId, Pageable pageable);

    boolean updateThoiGianKetNoiLaiByMo(Integer moId, Date thoiGianKetNoiLai);

    Optional<LichSuKetNoi> save(LichSuKetNoi lichSuKetNoi);

    Optional<LichSuKetNoi> update(LichSuKetNoi lichSuKetNoi);
}
