package com.tavi.tavi_mrs.service.thong_so_thong_bao;

import com.tavi.tavi_mrs.entities.thong_so_thong_bao.ThongSoThongBao;

import java.util.Optional;

public interface ThongSoThongBaoService {

    Optional<ThongSoThongBao> findByIdThongSoThongBao(int idThongSoThongBao);
}
