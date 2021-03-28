package com.tavi.tavi_mrs.entities.hinh_anh;


import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.vi_pham.ViPham;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Video_Luu_Tru", schema = "dbo")
public class VideoLuuTru extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_video_luu_tru")
    private Integer idVideoLuuTru;

    @Column(name = "duong_dan")
    private String duongDan;

    @ManyToOne
    @JoinColumn(name = "vi_pham_id")
    private ViPham viPham;
}
