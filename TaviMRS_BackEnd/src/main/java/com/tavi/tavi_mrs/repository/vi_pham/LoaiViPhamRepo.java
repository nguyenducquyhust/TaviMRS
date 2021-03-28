package com.tavi.tavi_mrs.repository.vi_pham;

import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoaiViPhamRepo extends JpaRepository<LoaiViPham,Integer> {

    Optional<LoaiViPham> findByIdLoaiViPhamAndXoa(int idLoaiViPham, boolean xoa);

    @Query(value = "from LoaiViPham l where l.xoa = ?1 order by l.idLoaiViPham asc")
    List<LoaiViPham> findAllByXoa(Boolean xoa);
}
