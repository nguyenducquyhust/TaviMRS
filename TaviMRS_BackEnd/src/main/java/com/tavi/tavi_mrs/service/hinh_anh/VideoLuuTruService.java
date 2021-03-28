package com.tavi.tavi_mrs.service.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.VideoLuuTru;

import java.util.List;
import java.util.Optional;

public interface VideoLuuTruService {
    List<VideoLuuTru> findByViPham(int viPhamId);
}
