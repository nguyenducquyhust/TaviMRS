package com.tavi.tavi_mrs.entities.thu_tuc;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
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
@Table(name = "Giay_Phep_Theo_Loai_Thu_Tuc", schema = "dbo")
public class GiayPhepTheoLoaiThuTuc extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_giay_phep_theo_loai_thu_tuc")
    private Integer idGiayPhepTheoLoaiThuTuc;

    @Column(name = "ten_giay_phep")
    private String tenGiayPhep;

    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "loai_thu_tuc_id")
    private LoaiThuTuc loaiThuTuc;

    private Boolean xoa;

}
