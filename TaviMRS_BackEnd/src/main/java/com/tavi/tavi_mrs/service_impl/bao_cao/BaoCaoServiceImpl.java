package com.tavi.tavi_mrs.service_impl.bao_cao;

import com.tavi.tavi_mrs.entities.bao_cao.BaoCao;
import com.tavi.tavi_mrs.repository.bao_cao.BaoCaoRepo;
import com.tavi.tavi_mrs.repository.doanh_nghiep.DoanhNghiepRepo;
import com.tavi.tavi_mrs.service.bao_cao.BaoCaoService;
import com.tavi.tavi_mrs.service_impl.thiet_bi.ThietBiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BaoCaoServiceImpl implements BaoCaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaoCaoServiceImpl.class);

    @Autowired
    private BaoCaoRepo baoCaoRepo;

    @Override
    public Optional<BaoCao> findByIdBaoCaoAndXoa(int idBaoCao, boolean xoa) {
        try {
            return baoCaoRepo.findByIdBaoCaoAndXoa(idBaoCao, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdBaoCaoAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<BaoCao> findAllToPage(Pageable pageable) {
        try {
            return baoCaoRepo.findAllToPage(pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllToPage error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<BaoCao> findByThongBao(int idThongBao, Pageable pageable) {
        try {
            return baoCaoRepo.findByThongBao(idThongBao, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByThongBao error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<BaoCao> findByTieuDeAndThoiGian(String tieuDe, Date ngayDau, Date ngayCuoi, Pageable pageable) {
        try {
            return baoCaoRepo.findByTieuDeAndThoiGian(tieuDe, ngayDau, ngayCuoi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByTieuDeAndThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<BaoCao> findByTieuDeAndDoanhNghiepAndThoiGian(String tieuDe, String doanhNghiep, Date ngayDau, Date ngayCuoi, Pageable pageable) {
        try {
            return baoCaoRepo.findByTieuDeAndDoanhNghiepAndThoiGian(tieuDe, doanhNghiep, ngayDau, ngayCuoi, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByTieuDeAndThoiGian error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
