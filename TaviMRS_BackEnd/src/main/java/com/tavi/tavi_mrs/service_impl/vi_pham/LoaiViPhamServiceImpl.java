package com.tavi.tavi_mrs.service_impl.vi_pham;

import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;
import com.tavi.tavi_mrs.repository.vi_pham.LoaiViPhamRepo;
import com.tavi.tavi_mrs.service.vi_pham.LoaiViPhamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiViPhamServiceImpl implements LoaiViPhamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiViPhamServiceImpl.class);

    @Autowired
    private LoaiViPhamRepo loaiViPhamRepo;

    @Override
    public Optional<LoaiViPham> findByIdLoaiViPhamAndXoa(int idLoaiViPham, boolean xoa) {
        try {
            return loaiViPhamRepo.findByIdLoaiViPhamAndXoa(idLoaiViPham, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdLoaiViPhamAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<LoaiViPham> findAllByXoa(Boolean xoa) {
        try {
            return loaiViPhamRepo.findAllByXoa(xoa);
        } catch (Exception ex) {
            LOGGER.error("findAllByXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<LoaiViPham> save(LoaiViPham loaiViPham) {
        try {
            return Optional.ofNullable(loaiViPhamRepo.save(loaiViPham));
        } catch (Exception ex) {
            LOGGER.error("saveLoaiViPham error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
