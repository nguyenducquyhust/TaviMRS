package com.tavi.tavi_mrs.entities.thiet_bi;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.mo.Mo;
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
@Table(name = "Thiet_Bi", schema = "dbo")
public class ThietBi extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_thiet_bi")
    private Integer idThietBi;

    @Column(name = "ma_thiet_bi")
    private String maThietBi;

    @Column(name = "ten_thiet_bi")
    private String tenThietBi;

    @Column(name = "vi_tri_lap")
    private String viTriLap;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "trang_thai_ket_noi")
    private Integer trangThaiKetNoi;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ly_do_duyet")
    private String lyDoDuyet;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

    @ManyToOne
    @JoinColumn(name = "doanh_nghiep_id")
    private DoanhNghiep doanhNghiep;

    private Boolean xoa;
}
