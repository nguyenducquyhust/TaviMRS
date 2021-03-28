package com.tavi.tavi_mrs.service.vi_pham;

import com.tavi.tavi_mrs.entities.vi_pham.LoaiViPham;

import java.util.List;
import java.util.Optional;

public interface LoaiViPhamService {

    Optional<LoaiViPham> findByIdLoaiViPhamAndXoa(int idLoaiViPham, boolean xoa);

    List<LoaiViPham> findAllByXoa(Boolean xoa);

    Optional<LoaiViPham> save(LoaiViPham loaiViPham);
}
