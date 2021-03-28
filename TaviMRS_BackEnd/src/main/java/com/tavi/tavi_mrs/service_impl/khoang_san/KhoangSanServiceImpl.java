package com.tavi.tavi_mrs.service_impl.khoang_san;

import com.tavi.tavi_mrs.entities.bieu_do.BieuDo;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
import com.tavi.tavi_mrs.repository.khoang_san.KhoangSanRepo;
import com.tavi.tavi_mrs.service.khoang_san.KhoangSanService;
import com.tavi.tavi_mrs.service_impl.thiet_bi.ThietBiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KhoangSanServiceImpl implements KhoangSanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KhoangSanServiceImpl.class);

    @Autowired
    private KhoangSanRepo khoangSanRepo;

    @Override
    public Optional<KhoangSan> findByIdKhoangSanAndXoa(Integer idKhoangSan, boolean xoa) {
        try {
            return khoangSanRepo.findByIdKhoangSanAndXoa(idKhoangSan, false);
        } catch (Exception ex) {
            LOGGER.error("findByIdKhoangSanAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<KhoangSan> findAll() {
        try {
            return khoangSanRepo.findAll();
        } catch (Exception ex) {
            LOGGER.error("findAllByXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<KhoangSan> findByTenAndNhomKhoangSanId(String ten, int idNhomKhoangSan, Pageable pageable) {
        try {
            return khoangSanRepo.findByTenAndNhomKhoangSanId(ten, idNhomKhoangSan, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByTenAndNhomKhoangSanId error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<KhoangSan> findAllToPage(Pageable pageable) {
        try {
            return khoangSanRepo.findAllToPage(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<KhoangSan> findByNhomKhoangSan(int nhomKhoangSanId) {
        try {
            return khoangSanRepo.findByNhomKhoangSan(nhomKhoangSanId);
        } catch (Exception ex) {
            LOGGER.error("findByNhomKhoangSanAndXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<KhoangSan> save(KhoangSan khoangSan) {
        try {
            return Optional.ofNullable(khoangSanRepo.save(khoangSan));
        } catch (Exception ex) {
            LOGGER.error("saveKhoangSan error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
