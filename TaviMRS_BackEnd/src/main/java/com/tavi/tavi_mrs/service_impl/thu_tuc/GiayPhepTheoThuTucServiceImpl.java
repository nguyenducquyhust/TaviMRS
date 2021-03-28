package com.tavi.tavi_mrs.service_impl.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoThuTuc;
import com.tavi.tavi_mrs.repository.thu_tuc.GiayPhepTheoThuTucRepo;
import com.tavi.tavi_mrs.service.thu_tuc.GiayPhepTheoThuTucService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiayPhepTheoThuTucServiceImpl  implements GiayPhepTheoThuTucService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiayPhepTheoThuTucServiceImpl.class.getName());

    @Autowired
    GiayPhepTheoThuTucRepo giayPhepTheoThuTucRepo;

    @Override
    public Optional<GiayPhepTheoThuTuc> findByIdGiayPhepTheoThuTuc(int idGiayPhepTheoThuTuc) {
        try {
            return giayPhepTheoThuTucRepo.findByIdGiayPhepTheoThuTuc(idGiayPhepTheoThuTuc);
        } catch (Exception ex) {
            LOGGER.error("findByIdGiayPhepTheoThuTuc error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<GiayPhepTheoThuTuc> findByThuTuc(int thuTucId) {
        try {
            return giayPhepTheoThuTucRepo.findByThuTuc(thuTucId);
        } catch (Exception ex) {
            LOGGER.error("findByThuTuc error", ex.getCause());
            return null;
        }
    }
}
