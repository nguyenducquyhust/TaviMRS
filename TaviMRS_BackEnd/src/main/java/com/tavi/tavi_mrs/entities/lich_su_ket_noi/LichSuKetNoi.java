package com.tavi.tavi_mrs.entities.lich_su_ket_noi;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.base.CanhBao;
import com.tavi.tavi_mrs.entities.mo.Mo;
import com.tavi.tavi_mrs.entities.thiet_bi.ThietBi;
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
@Builder
@Table(name = "Lich_Su_Ket_Noi", schema = "dbo")
public class  LichSuKetNoi extends CanhBao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lich_su_ket_noi")
    private Integer idLichSuKetNoi;

    @ManyToOne
    @JoinColumn(name = "mo_id")
    private Mo mo;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "nguyen_nhan")
    private String nguyenNhan;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "thoi_gian_ket_noi_lai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianKetNoiLai;

    @ManyToOne
    @JoinColumn(name = "thong_bao_id")
    private ThongBao thongBao;

    private Boolean xoa;
}
