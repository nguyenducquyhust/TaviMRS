package com.tavi.tavi_mrs.service.lich_su_ket_noi;

import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoiThietBi;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LichSuKetNoiThietBiService {

    Optional<LichSuKetNoiThietBi> findByIdLichSuKetNoiThietBiAndXoa(int idLichSuKetNoiThietBi, boolean xoa);

    List<LichSuKetNoiThietBi> findByLichSuKetNoiId(int lichSuKetNoiId);

    Optional<LichSuKetNoiThietBi> save(LichSuKetNoiThietBi lichSuKetNoiThietBi);

    boolean deleteById(int idLichSuKetNoiThietBi);
}
