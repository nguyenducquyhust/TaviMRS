package com.tavi.tavi_mrs.service_impl.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoLoaiThuTuc;
import com.tavi.tavi_mrs.repository.thu_tuc.GiayPhepTheoLoaiThuTucRepo;
import com.tavi.tavi_mrs.service.thu_tuc.GiayPhepTheoLoaiThuTucService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiayPhepTheoLoaiThuTucServiceImpl implements GiayPhepTheoLoaiThuTucService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiayPhepTheoLoaiThuTucServiceImpl.class.getName());

    @Autowired
    GiayPhepTheoLoaiThuTucRepo giayPhepTheoLoaiThuTucRepo;

    @Override
    public Optional<GiayPhepTheoLoaiThuTuc> findByIdGiayPhepTheoLoaiThuTuc(int idGiayPhepTheoLoaiThuTuc) {
        try {
            return giayPhepTheoLoaiThuTucRepo.findByIdGiayPhepTheoLoaiThuTuc(idGiayPhepTheoLoaiThuTuc);
        } catch (Exception ex) {
            LOGGER.error("findByIdGiayPhepTheoLoaiThuTuc error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<GiayPhepTheoLoaiThuTuc> findByLoaiThuTuc(int loaiThuTucId) {
        try {
            return giayPhepTheoLoaiThuTucRepo.findByLoaiThuTuc(loaiThuTucId);
        } catch (Exception ex) {
            LOGGER.error("findByLoaiThuTuc error", ex.getCause());
            return null;
        }
    }
}
