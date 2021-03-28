package com.tavi.tavi_mrs.repository.thiet_bi;

import com.tavi.tavi_mrs.entities.thiet_bi.Camera;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CameraRepo extends JpaRepository<Camera,Integer> {

    @Query(value = "from Camera c where (?1 is null or c.idCamera = ?1 and c.xoa = false) " +
            " order by c.thuTuUuTien asc ")
    Optional<Camera> findByIdCameraAndXoa(int idCamera , boolean xoa);

    Page<Camera> findAllByXoa(boolean xoa , Pageable pageable);

    @Query(value = "from Camera c where (0 = ?1 or c.thietBi.idThietBi = ?1 and c.thietBi.xoa = false) " +
            "and c.xoa = false order by c.thuTuUuTien asc ")
    Page<Camera> findByThietBiIdAndXoa(int idThietBi,boolean xoa, Pageable pageable);

    @Query(value = "from Camera  c where (0 = ?1 or c.thietBi.doanhNghiep.idDoanhNghiep = ?1 and c.thietBi.xoa = false and c.thietBi.doanhNghiep.xoa = false)" +
            "and c.xoa = ?2 order by c.thuTuUuTien asc")
    Page<Camera> findByDoanhNghiepAndXoa(int idDoanhNghiep, boolean xoa, Pageable pageable);

    @Query(value = "from Camera  c where (0 = ?1 or c.thietBi.mo.idMo = ?1 and c.thietBi.xoa = false and c.thietBi.mo.xoa = false)" +
            "and c.xoa = false order by c.thuTuUuTien asc")
    Page<Camera> findByMo(int idMo, Pageable pageable);

}
