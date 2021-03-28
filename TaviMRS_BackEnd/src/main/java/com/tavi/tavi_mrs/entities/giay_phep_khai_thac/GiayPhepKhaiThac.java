package com.tavi.tavi_mrs.entities.giay_phep_khai_thac;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.mo.Mo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Giay_Phep_Khai_Thac", schema = "dbo")
public class GiayPhepKhaiThac extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_giay_phep")
    private Integer idGiayPhep;

    @Column(name = "so_quyet_dinh")
    private String soQuyetDinh;

    @Column(name = "co_quan_cap")
    private String coQuanCap;

    @Column(name = "nguoi_ky")
    private String nguoiKy;

    @Column(name = "chuc_vu")
    private String chucVu;

    @ManyToOne
    @JoinColumn(name = "loai_giay_phep_id")
    private LoaiGiayPhep loaiGiayPhep;

    @Column(name = "ngay_cap")
    @Temporal(TemporalType.DATE)
    private Date ngayCap;

    @Column(name = "thoi_han_khai_thac")
    private Integer thoiHanKhaiThac;

    @Column(name = "tru_luong_dia_chat")
    private Double truLuongDiaChat;

    @Column(name = "tru_luong_khoang_san")
    private Double truLuongKhoangSan;

    @Column(name = "cong_suat_khai_thac")
    private Double congSuatKhaiThac;

    @Column(name = "dien_tich_khai_thac")
    private Double dienTichKhaiThac;

    @Column(name = "giay_phep")
    private String giayPhep;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "tham_quyen_cap")
    private Integer thamQuyenCap;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

    @ManyToOne
    @JoinColumn(name = "doanh_nghiep_id")
    private DoanhNghiep doanhNghiep;

    @Column(name = "ngay_het_han")
    @Temporal(TemporalType.DATE)
    private Date ngayHetHan;

    private Boolean xoa;

}
