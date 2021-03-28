package com.tavi.tavi_mrs.service.man_hinh_theo_doi;

import com.tavi.tavi_mrs.entities.man_hinh_theo_doi.ThietBiMatKetNoi;

import java.util.List;

public interface ThongTinKetNoiMoService {

    List<ThietBiMatKetNoi> pingMo(String urlMo);
}
