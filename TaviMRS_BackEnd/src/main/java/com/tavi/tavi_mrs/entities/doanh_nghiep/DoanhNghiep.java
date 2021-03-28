package com.tavi.tavi_mrs.entities.doanh_nghiep;


import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.cms.organization.ThongTinDoanhNghiep;
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
@Table(name = "Doanh_Nghiep", schema = "dbo")
public class DoanhNghiep extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_doanh_nghiep")
    private Integer idDoanhNghiep;

    @Column(name = "thong_tin_doanh_nghiep_id")
    private Integer thongTinDoanhNghiepId;

    @Column(name = "ten_doanh_nghiep")
    private String tenDoanhNghiep;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "huyen_id")
    private Integer huyenId;

    @Column(name = "xa_id")
    private Integer xaId;

    @Transient
    private ThongTinDoanhNghiep thongTinDoanhNghiep;

    @Column(name = "trang_thai_phan_mem")
    private Integer trangThaiPhanMem;

    private String khoa;

    @Column(name = "ma_ket_noi")
    private String maKetNoi;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai_hoat_dong")
    private Integer trangThaiHoatDong;

    private Boolean xoa;

    private static String generateKhoa(String keyToEncode) {
        return keyToEncode;
    }

    private static String generateMaKetNoi(String keyToEncode) {
        return keyToEncode;
    }
}
