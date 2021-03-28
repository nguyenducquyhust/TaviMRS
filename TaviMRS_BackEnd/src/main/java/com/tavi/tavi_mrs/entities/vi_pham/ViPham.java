package com.tavi.tavi_mrs.entities.vi_pham;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.base.CanhBao;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.thong_bao.ThongBao;
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
@Table(name = "Vi_Pham", schema = "dbo")
public class ViPham extends CanhBao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vi_pham")
    private Integer idViPham;

    @ManyToOne
    @JoinColumn(name = "loai_vi_pham_id")
    private LoaiViPham loaiViPham;

    @Column(name = "noi_dung_vi_pham")
    private String noiDungViPham;

    @Column(name = "dia_diem_vi_pham")
    private String diaDiemViPham;

    @Column(name = "thoi_gian_xa_ra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianXayRa;

    @Column(name = "trang_thai_xu_ly")
    private Integer trangThaiXuLy;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

    @ManyToOne
    @JoinColumn(name = "doanh_nghiep_id")
    private DoanhNghiep doanhNghiep;

    @Column(name = "thoi_gian_thong_bao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianThongBao;

    @ManyToOne
    @JoinColumn(name = "thong_bao_id")
    private ThongBao thongBao;

    private Boolean xoa;
}
