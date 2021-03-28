package com.tavi.tavi_mrs.service.thiet_bi;

import com.tavi.tavi_mrs.entities.thiet_bi.Camera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CameraService {

    Optional<Camera> findByIdCameraAndXoa(int idCamera , boolean xoa);

    Page<Camera> findAllByXoa(boolean xoa , Pageable pageable);

    Page<Camera> findByThietBiIdAndXoa(int idThietBi,boolean xoa, Pageable pageable);

    Page<Camera> findByMo(int idMo, Pageable pageable);

    Optional<Camera> save(Camera camera);;

    Page<Camera> findByDoanhNghiep(int idDoanhNghiep, Pageable pageable);
}
