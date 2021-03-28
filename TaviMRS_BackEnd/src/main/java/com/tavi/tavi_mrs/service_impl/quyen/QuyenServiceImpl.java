package com.tavi.tavi_mrs.service_impl.quyen;

import com.tavi.tavi_mrs.entities.quyen.Quyen;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
import com.tavi.tavi_mrs.repository.quyen.NhomQuyenRepo;
import com.tavi.tavi_mrs.repository.quyen.QuyenRepo;
import com.tavi.tavi_mrs.service.quyen.QuyenService;
import com.tavi.tavi_mrs.service_impl.thiet_bi.ThietBiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class QuyenServiceImpl implements QuyenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThietBiServiceImpl.class);

    @Autowired
    private QuyenRepo quyenRepo;

    @Override
    public Optional<Quyen> findByIdQuyen(int idQuyen) {
        try {
            return quyenRepo.findByIdQuyen(idQuyen);
        } catch (Exception ex) {
            LOGGER.error("findByIdQuyen error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<Quyen> findAllToPage(Pageable pageable) {
        try {
            return quyenRepo.findAllToPage(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Quyen> save(Quyen quyen) {
        try {
            return Optional.ofNullable(quyenRepo.save(quyen));
        } catch (Exception ex) {
            LOGGER.error("saveQuyen error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
