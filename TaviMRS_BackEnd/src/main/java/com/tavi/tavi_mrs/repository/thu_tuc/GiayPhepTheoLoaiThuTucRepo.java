package com.tavi.tavi_mrs.repository.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoLoaiThuTuc;
import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoThuTuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiayPhepTheoLoaiThuTucRepo extends JpaRepository<GiayPhepTheoLoaiThuTuc,Integer>{

    @Query(value = "select g from GiayPhepTheoLoaiThuTuc g where g.xoa =false and g.idGiayPhepTheoLoaiThuTuc = ?1  ")
    Optional<GiayPhepTheoLoaiThuTuc> findByIdGiayPhepTheoLoaiThuTuc(int idGiayPhepTheoLoaiThuTuc);

    @Query(value = "select g from GiayPhepTheoLoaiThuTuc g where  g.xoa =false and g.loaiThuTuc.idLoaiThuTuc = ?1 ")
    List<GiayPhepTheoLoaiThuTuc> findByLoaiThuTuc(int loaiThuTucId);
}
