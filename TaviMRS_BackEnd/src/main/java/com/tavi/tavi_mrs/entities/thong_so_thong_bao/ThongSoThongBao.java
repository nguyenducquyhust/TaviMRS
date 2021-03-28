package com.tavi.tavi_mrs.entities.thong_so_thong_bao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Thong_So_Thong_Bao", schema = "dbo")
public class ThongSoThongBao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_thong_so_thong_bao")
    private Integer idThongSoThongBao;

    @Column(name = "thoi_gian_bao_truoc")
    private Integer thoiGianBaoTruoc;

    @Column(name = "noi_dung_thong_bao")
    private String noiDungThongBao;

    @Column(name = "hinh_thuc_thong_bao")
    private Integer hinhThucThongBao;

    @Column(name = "nguoi_nhan")
    private Integer nguoiNhan;
}
