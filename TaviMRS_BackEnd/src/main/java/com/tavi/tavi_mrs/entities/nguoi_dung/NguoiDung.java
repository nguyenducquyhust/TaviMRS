package com.tavi.tavi_mrs.entities.nguoi_dung;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.quyen.NhomQuyen;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//import com.tavi.tavi_mrs.entities.quyen.NhomQuyen;
//import com.tavi.tavi_mrs.entities.quyen.NhomQuyen;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Nguoi_Dung", schema = "dbo")
public class NguoiDung extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nguoi_dung")
    private Integer idNguoiDung;

    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "thong_tin_id")
    private Integer thongTinId;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

    @Column(name = "chuc_vu")
    private String chucVu;

    private String email;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "doanh_nghiep_id")
    private DoanhNghiep doanhNghiep;

//    @ManyToMany(mappedBy = "thongBaoCoNguoiNhan")
//    private List<ThongBao> danhSachThongBao;

    @ManyToMany
    @JoinTable(name = "Nguoi_Dung_Co_Nhom_Quyen",
            joinColumns = @JoinColumn(name = "nguoi_dung_id"),
            inverseJoinColumns = @JoinColumn(name = "nhom_quyen_id"))
    private List<NguoiDung> nguoiDungCoNhomQuyen;

    private Boolean xoa;
}
