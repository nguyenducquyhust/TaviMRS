package com.tavi.tavi_mrs.service_impl.quyen;

import com.tavi.tavi_mrs.entities.quyen.NhomQuyen;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import com.tavi.tavi_mrs.repository.quyen.NhomQuyenRepo;
import com.tavi.tavi_mrs.service.quyen.NhomQuyenService;
import com.tavi.tavi_mrs.service_impl.thiet_bi.ThietBiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class NhomQuyenServiceImpl implements NhomQuyenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThietBiServiceImpl.class);

    @Autowired
    private NhomQuyenRepo nhomQuyenRepo;

    @Override
    public Optional<NhomQuyen> findByIdNhomQuyen(int idNhomQuyen) {
        try {
            return nhomQuyenRepo.findByIdNhomQuyen(idNhomQuyen);
        } catch (Exception ex) {
            LOGGER.error("findByIdNhomQuyen error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomQuyen> findAllToPage(Pageable pageable) {
        try {
            return nhomQuyenRepo.findAllToPage(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<NhomQuyen> save(NhomQuyen nhomQuyen) {
        try {
            return Optional.ofNullable(nhomQuyenRepo.save(nhomQuyen));
        } catch (Exception ex) {
            LOGGER.error("saveNhomQuyen error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
