package com.tavi.tavi_mrs.entities.vi_pham;

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
@Table(name = "Loai_Vi_Pham", schema = "dbo")
public class LoaiViPham extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loai_vi_pham")
    private Integer idLoaiViPham;

    @Column(name = "vi_pham")
    private String viPham;

    private String icon;

    @Column(name = "mo_ta")
    private String moTa;

    private Boolean xoa;
}
