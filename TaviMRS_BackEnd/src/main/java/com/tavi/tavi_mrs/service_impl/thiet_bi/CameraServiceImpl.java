package com.tavi.tavi_mrs.service_impl.thiet_bi;

import com.tavi.tavi_mrs.entities.thiet_bi.Camera;
import com.tavi.tavi_mrs.repository.thiet_bi.CameraRepo;
import com.tavi.tavi_mrs.service.thiet_bi.CameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CameraServiceImpl implements CameraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraServiceImpl.class);

    @Autowired
    private CameraRepo cameraRepo;

    @Override
    public Optional<Camera> findByIdCameraAndXoa(int idCamera, boolean xoa) {
        try {
            return cameraRepo.findByIdCameraAndXoa(idCamera, xoa);
        } catch (Exception ex) {
            LOGGER.error("findByIdCameraAndXoa error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<Camera> findAllByXoa(boolean xoa, Pageable pageable) {
        try {
            return cameraRepo.findAllByXoa(xoa, pageable);
        } catch (Exception ex) {
            LOGGER.error("findAllByXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Camera> findByThietBiIdAndXoa(int idThietBi, boolean xoa, Pageable pageable) {
        try {
            return cameraRepo.findByThietBiIdAndXoa(idThietBi , xoa, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByThietBiIdAndXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Camera> findByMo(int idMo, Pageable pageable) {
        try {
            return cameraRepo.findByMo(idMo , pageable);
        } catch (Exception ex) {
            LOGGER.error("findByThietBiIdAndXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Camera> save(Camera camera) {
        try {
            return Optional.of(cameraRepo.save(camera));
        } catch (Exception ex) {
            LOGGER.error("saveCamera error", ex);
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Page<Camera> findByDoanhNghiep(int idDoanhNghiep, Pageable pageable) {
        try {
            return cameraRepo.findByDoanhNghiepAndXoa(idDoanhNghiep , false, pageable);
        } catch (Exception ex) {
            LOGGER.error("findByDoanhNghiepAndXoa error", ex);
            ex.printStackTrace();
            return null;
        }
    }

}
