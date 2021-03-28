package com.tavi.tavi_mrs.repository.thong_so_thong_bao;

import com.tavi.tavi_mrs.entities.thong_so_thong_bao.ThongSoThongBao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThongSoThongBaoRepo extends JpaRepository<ThongSoThongBao, Integer> {

    Optional<ThongSoThongBao> findByIdThongSoThongBao(int idThongSoThongBao);
}
