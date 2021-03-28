package com.tavi.tavi_mrs.service_impl.canh_bao;

import com.tavi.tavi_mrs.entities.base.CanhBao;
import com.tavi.tavi_mrs.repository.lich_su_ket_noi.LichSuKetNoiRepo;
import com.tavi.tavi_mrs.repository.vi_pham.ViPhamRepo;
import com.tavi.tavi_mrs.service.canh_bao.CanhBaoService;
import com.tavi.tavi_mrs.service_impl.bao_cao.BaoCaoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CanhBaoServiceImpl implements CanhBaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanhBaoServiceImpl.class);

    @Autowired
    private LichSuKetNoiRepo lichSuKetNoiRepo;

    @Autowired
    private ViPhamRepo viPhamRepo;

    @Override
    public List<CanhBao> findCanhBaoManHinhTheoDoi() {
        try {
            List<CanhBao> canhBaoList = new ArrayList<>();
            canhBaoList.addAll(lichSuKetNoiRepo.findCanhBaoManHinhTheoDoi());
            canhBaoList.addAll(viPhamRepo.findCanhBaoManHinhTheoDoi());
            canhBaoList.sort(Comparator.comparing(CanhBao::getThoiGianXayRa).reversed());
            return canhBaoList;
        } catch (Exception ex) {
            LOGGER.error("findByTrangThaiXuLy error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
