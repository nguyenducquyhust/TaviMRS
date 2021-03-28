package com.tavi.tavi_mrs.entities.giay_phep_khai_thac;

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
@Table(name = "Loai_Giay_Phep", schema = "dbo")
public class LoaiGiayPhep extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loai_giay_phep")
    private Integer idLoaiGiayPhep;

    @Column(name = "loai_giay_phep")
    private String loaiGiayPhep;

    @Column(name = "mo_ta")
    private String moTa;

    private Boolean xoa;

}
