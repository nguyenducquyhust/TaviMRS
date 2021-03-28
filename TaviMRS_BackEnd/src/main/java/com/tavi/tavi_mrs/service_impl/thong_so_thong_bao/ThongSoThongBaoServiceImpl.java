package com.tavi.tavi_mrs.service_impl.thong_so_thong_bao;

import com.tavi.tavi_mrs.entities.thong_so_thong_bao.ThongSoThongBao;
import com.tavi.tavi_mrs.repository.thong_so_thong_bao.ThongSoThongBaoRepo;
import com.tavi.tavi_mrs.service.thong_so_thong_bao.ThongSoThongBaoService;
import com.tavi.tavi_mrs.service_impl.thiet_bi.ThietBiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThongSoThongBaoServiceImpl implements ThongSoThongBaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThongSoThongBaoServiceImpl.class);

    @Autowired
    private ThongSoThongBaoRepo thongSoThongBaoRepo;

    @Override
    public Optional<ThongSoThongBao> findByIdThongSoThongBao(int idThongSoThongBao) {
        try {
            return thongSoThongBaoRepo.findByIdThongSoThongBao(idThongSoThongBao);
        } catch (Exception ex) {
            LOGGER.error("findByIdThongSoThongBao error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
