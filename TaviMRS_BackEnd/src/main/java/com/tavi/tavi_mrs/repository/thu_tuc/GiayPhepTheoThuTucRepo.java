package com.tavi.tavi_mrs.repository.thu_tuc;

import com.tavi.tavi_mrs.entities.thu_tuc.GiayPhepTheoThuTuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface GiayPhepTheoThuTucRepo extends JpaRepository<GiayPhepTheoThuTuc, Integer> {

    @Query(value = "select g from GiayPhepTheoThuTuc g where g.xoa =false and g.idGiayPhepTheoThuTuc = ?1")
    Optional<GiayPhepTheoThuTuc> findByIdGiayPhepTheoThuTuc(int idGiayPhepTheoThuTuc);

    @Query(value = "select g from GiayPhepTheoThuTuc g where g.xoa =false and g.thuTuc.idThuTuc = ?1")
    List<GiayPhepTheoThuTuc> findByThuTuc(int thuTucId);
}
