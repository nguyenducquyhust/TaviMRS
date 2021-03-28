package com.tavi.tavi_mrs.repository.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.VideoLuuTru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoLuuTruRepo extends JpaRepository<VideoLuuTru, Integer> {

    @Query(value = "from VideoLuuTru v where (0 = ?1 or v.viPham.idViPham = ?1 and v.viPham.xoa = false) order by v.idVideoLuuTru asc ")
    List<VideoLuuTru> findByViPham(int idViPham);

}
