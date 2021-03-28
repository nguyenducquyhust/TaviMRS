package com.tavi.tavi_mrs.service_impl.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.LoaiGiayPhep;
import com.tavi.tavi_mrs.repository.giay_phep_khai_thac.GiayPhepKhaiThacRepo;
import com.tavi.tavi_mrs.repository.giay_phep_khai_thac.LoaiGiayPhepRepo;
import com.tavi.tavi_mrs.service.giay_phep_khai_thac.GiayPhepKhaiThacService;
import com.tavi.tavi_mrs.service.giay_phep_khai_thac.LoaiGiayPhepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiGiayPhepServiceImpl implements LoaiGiayPhepService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiGiayPhepServiceImpl.class);

    @Autowired
    private LoaiGiayPhepRepo loaiGiayPhepRepo;

    @Override
    public Optional<LoaiGiayPhep> findByIdLoaiGiayPhepAndXoa(int idLoaiGiayPhep, boolean xoa) {
        try {
            return loaiGiayPhepRepo.findByIdLoaiGiayPhepAndXoa(idLoaiGiayPhep, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdLoaiGiayPhepAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<LoaiGiayPhep> findAllByXoa(Boolean xoa) {
        try {
            return loaiGiayPhepRepo.findAllByXoa(xoa);
        } catch (Exception ex) {
            LOGGER.error("findAll error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<LoaiGiayPhep> save(LoaiGiayPhep loaiGiayPhep) {
        try {
            return Optional.ofNullable(loaiGiayPhepRepo.save(loaiGiayPhep));
        } catch (Exception ex) {
            LOGGER.error("saveLoaiGiayPhep error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int idLoaiGiayPhep) {
        try {
            return loaiGiayPhepRepo.deleteByIdLoaiGiayPhep(idLoaiGiayPhep) > 0;
        } catch (Exception ex) {
            LOGGER.error("deleteByIdLoaiGiayPhep error", ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<LoaiGiayPhep> findAll(Pageable pageable) {
        try {
            return loaiGiayPhepRepo.findAll(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAll error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
