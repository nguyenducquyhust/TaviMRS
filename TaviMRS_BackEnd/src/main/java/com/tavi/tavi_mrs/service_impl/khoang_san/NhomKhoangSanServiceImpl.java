package com.tavi.tavi_mrs.service_impl.khoang_san;

import com.tavi.tavi_mrs.entities.khoang_san.NhomKhoangSan;
import com.tavi.tavi_mrs.repository.khoang_san.NhomKhoangSanRepo;
import com.tavi.tavi_mrs.service.khoang_san.NhomKhoangSanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhomKhoangSanServiceImpl implements NhomKhoangSanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KhoangSanServiceImpl.class);

    @Autowired
    private NhomKhoangSanRepo nhomKhoangSanRepo;

    @Override
    public Optional<NhomKhoangSan> findByIdNhomKhoangSanAndXoa(int idNhomKhoangSan, boolean xoa) {
        try {
            return nhomKhoangSanRepo.findByIdNhomKhoangSanAndXoa(idNhomKhoangSan, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdNhomKhoangSanAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<NhomKhoangSan> findByTen(String ten, Pageable pageable) {
        try {
            return nhomKhoangSanRepo.findByTen(ten, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByTen error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<NhomKhoangSan> save(NhomKhoangSan nhomKhoangSan) {
        try {
            return Optional.ofNullable(nhomKhoangSanRepo.save(nhomKhoangSan));
        } catch (Exception ex) {
            LOGGER.error("saveNhomKhoangSan error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<NhomKhoangSan> findAllByXoa(Boolean xoa) {
        try {
            return nhomKhoangSanRepo.findAllByXoa( xoa);
        } catch (Exception ex) {
            LOGGER.error("findAllByXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
