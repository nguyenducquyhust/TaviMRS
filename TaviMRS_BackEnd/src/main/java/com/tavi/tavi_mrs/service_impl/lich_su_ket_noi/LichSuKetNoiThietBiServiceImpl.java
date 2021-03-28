package com.tavi.tavi_mrs.service_impl.lich_su_ket_noi;

import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoi;
import com.tavi.tavi_mrs.entities.lich_su_ket_noi.LichSuKetNoiThietBi;
import com.tavi.tavi_mrs.repository.lich_su_ket_noi.LichSuKetNoiRepo;
import com.tavi.tavi_mrs.repository.lich_su_ket_noi.LichSuKetNoiThietBiRepo;
import com.tavi.tavi_mrs.service.lich_su_ket_noi.LichSuKetNoiService;
import com.tavi.tavi_mrs.service.lich_su_ket_noi.LichSuKetNoiThietBiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LichSuKetNoiThietBiServiceImpl implements LichSuKetNoiThietBiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LichSuKetNoiThietBiServiceImpl.class);

    @Autowired
    private LichSuKetNoiThietBiRepo lichSuKetNoiThietBiRepo;


    @Override
    public Optional<LichSuKetNoiThietBi> findByIdLichSuKetNoiThietBiAndXoa(int idLichSuKetNoiThietBi, boolean xoa) {
        try {
            return lichSuKetNoiThietBiRepo.findByIdLichSuKetNoiThietBiAndXoa(idLichSuKetNoiThietBi, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdLichSuKetNoiThietBiAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<LichSuKetNoiThietBi> findByLichSuKetNoiId(int lichSuKetNoiId) {
        try {
            return lichSuKetNoiThietBiRepo.findByLichSuKetNoiId(lichSuKetNoiId);
        } catch (Exception ex) {
            LOGGER.error("findByLichSuKetNoiId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<LichSuKetNoiThietBi> save(LichSuKetNoiThietBi lichSuKetNoiThietBi) {
        try {
            return Optional.ofNullable(lichSuKetNoiThietBiRepo.save(lichSuKetNoiThietBi));
        } catch (Exception ex) {
            LOGGER.error("saveLichSuKetNoi error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int idLichSuKetNoiThietBi) {
        try {
            return lichSuKetNoiThietBiRepo.deleteByIdLichSuKetNoiThietBi(idLichSuKetNoiThietBi) > 0;
        } catch (Exception ex) {
            LOGGER.error("deleteByIdLichSuKetNoiThietBi error", ex);
            ex.printStackTrace();
            return false;
        }
    }
}

