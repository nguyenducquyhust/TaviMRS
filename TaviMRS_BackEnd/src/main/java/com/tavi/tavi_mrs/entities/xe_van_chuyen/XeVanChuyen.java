package com.tavi.tavi_mrs.entities.xe_van_chuyen;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
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
@Table(name = "Xe_Van_Chuyen", schema = "dbo")
public class XeVanChuyen extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_xe_van_chuyen")
    private Integer idXeVanChuyen;

    @Column(name = "bien_so_xe")
    private String bienSoXe;

    @Column(name = "so_quan_ly")
    private String soQuanLy;

    @Column(name = "loai_phuong_tien")
    private String loaiPhuongTien;

    @Column(name = "nhan_hieu")
    private String nhanHieu;

    @Column(name = "so_may")
    private String soMay;

    @Column(name = "so_khung")
    private String soKhung;

    @Column(name = "nam_san_xuat")
    private String namSanXuat;

    @Column(name = "nien_han_su_dung")
    private String nienHanSuDung;

    @Column(name = "khoi_luong_ban_than")
    private Double khoiLuongBanThan;

    @Column(name = "khoi_luong_hang")
    private Double khoiLuongHang;

    @Column(name = "giay_dang_kiem")
    private String giayDangKiem;

    @ManyToOne
    @JoinColumn(name = "doanh_nghiep_id")
    private DoanhNghiep doanhNghiep;

    private Integer trangThai;

    @Column(name = "ly_do_duyet")
    private String lyDoDuyet;

    private Boolean xoa;
}
