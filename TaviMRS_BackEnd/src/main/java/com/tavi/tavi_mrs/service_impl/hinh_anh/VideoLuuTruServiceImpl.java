package com.tavi.tavi_mrs.service_impl.hinh_anh;

import com.tavi.tavi_mrs.entities.hinh_anh.VideoLuuTru;
import com.tavi.tavi_mrs.repository.hinh_anh.VideoLuuTruRepo;
import com.tavi.tavi_mrs.service.hinh_anh.VideoLuuTruService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoLuuTruServiceImpl implements VideoLuuTruService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HinhAnhViPhamServiceImpl.class);

    @Autowired
    private VideoLuuTruRepo videoLuuTruRepo;

    @Override
    public List<VideoLuuTru> findByViPham(int viPhamId) {
        try {
            return videoLuuTruRepo.findByViPham(viPhamId);
        } catch (Exception ex) {
            LOGGER.error("findByViPham error", ex);
            ex.printStackTrace();
            return null;
        }
    }
}
