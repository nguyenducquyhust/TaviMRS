package com.tavi.tavi_mrs.entities.hinh_anh;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.mo.Mo;
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
@Table(name = "Hinh_Anh_Luu_Tru", schema = "dbo")
public class HinhAnhLuuTru extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hinh_anh")
    private Integer idHinhAnh;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "thoi_gian")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGian;

    @Column(name = "dia_diem")
    private String diaDiem;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    private Boolean xoa;
}
