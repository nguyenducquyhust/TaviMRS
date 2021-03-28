package com.tavi.tavi_mrs.entities.thu_tuc;


import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.nguoi_dung.NguoiDung;
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
@Table(name ="Loai_Thu_Tuc", schema = "dbo")
public class LoaiThuTuc extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loai_thu_tuc")
    private Integer idLoaiThuTuc;

    @Column(name = "loai_thu_tuc")
    private String loaiThuTuc;

    @Column(name = "mo_ta")
    private String moTa;

    private Boolean xoa;
}
