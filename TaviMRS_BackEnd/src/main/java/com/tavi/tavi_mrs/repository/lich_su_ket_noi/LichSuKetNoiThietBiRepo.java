package com.tavi.tavi_mrs.repository.lich_su_ket_noi;

import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoiThietBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LichSuKetNoiThietBiRepo extends JpaRepository<LichSuKetNoiThietBi, Integer> {

    Optional<LichSuKetNoiThietBi> findByIdLichSuKetNoiThietBiAndXoa(int idLichSuKetNoiThietBi, boolean xoa);

    @Query("from LichSuKetNoiThietBi l where (0 = ?1 or l.lichSuKetNoi.idLichSuKetNoi = ?1) and l.xoa = false")
    List<LichSuKetNoiThietBi> findByLichSuKetNoiId(int lichSuKetNoiId);

    @Transactional
    @Modifying
    @Query(value = "update LichSuKetNoiThietBi l set l.xoa = true where l.idLichSuKetNoiThietBi = ?1")
    int deleteByIdLichSuKetNoiThietBi(int idLichSuKetNoiThietBi);
}
