package com.tavi.tavi_mrs.entities.mo;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.khoang_san.KhoangSan;
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
@Table(name = "Mo", schema = "dbo")
public class Mo extends BaseEntity {

    @Id
    @Column(name = "id_mo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMo;

    @ManyToOne
    @JoinColumn(name = "doanh_nghiep_id")
    private DoanhNghiep doanhNghiep;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "huyen_id")
    private Integer huyenId;

    @Column(name = "tinh_id")
    private Integer tinhId;

    @ManyToOne
    @JoinColumn(name = "khoang_san_id")
    private KhoangSan khoangSan;

    @Column(name = "cong_suat_khai_thac")
    private Double congSuatKhaiThac;

    @Column(name = "tru_luong_mo")
    private Double truLuongMo;

    @Column(name = "tru_luong_con_lai")
    private Double truLuongConLai;

    @Column(name = "tru_luong_khai_thac_nam")
    private Double truLuongKhaiThacNam;

    @Column(name = "tru_luong_da_khai_thac_nam")
    private Double truLuongDaKhaiThacNam;

    @Column(name = "trang_thai_ket_noi")
    private Boolean trangThaiKetNoi;

    @Column(name = "trang_thai_hoat_dong")
    private Integer trangThaiHoatDong;

    private Boolean xoa;
}
