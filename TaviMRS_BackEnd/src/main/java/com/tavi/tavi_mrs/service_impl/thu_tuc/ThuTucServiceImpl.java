package com.tavi.tavi_mrs.service_impl.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.ThuTuc;
import com.tavi.tavi_mrs.repository.thu_tuc.ThuTucRepo;
import com.tavi.tavi_mrs.service.thu_tuc.ThuTucService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThuTucServiceImpl implements ThuTucService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThuTucServiceImpl.class);

    @Autowired
    private ThuTucRepo thuTucRepo;

    @Override
    public boolean deleteById(int idThuTuc) {
        try {
            return thuTucRepo.deleteByIdThuTuc(idThuTuc) > 0;
        } catch (Exception ex) {
            LOGGER.error("deleteByIdThuTuc error", ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<ThuTuc> findByIdThuTuc(int idThuTuc, Boolean xoa) {
        try {
            return thuTucRepo.findByIdThuTuc(idThuTuc,xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdThuTuc error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<ThuTuc> save(ThuTuc thuTuc) {
        try {
            return Optional.ofNullable(thuTucRepo.save(thuTuc));
        } catch (Exception ex) {
            LOGGER.error("saveThuTuc error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<ThuTuc> findAllByDoanhNghiep(int doanhNghiepId, Pageable pageable) {
        try {
            return thuTucRepo.findAllByDoanhNghiep(doanhNghiepId, pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllByDoanhNghiep error", ex.getCause());
            return null;
        }
    }
}
