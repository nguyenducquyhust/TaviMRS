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
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Lich_Su_Ket_Noi_Thiet_Bi", schema = "dbo")
public class LichSuKetNoiThietBi extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lich_su_ket_noi_thiet_bi")
    private Integer idLichSuKetNoiThietBi;

    @ManyToOne
    @JoinColumn(name = "thiet_bi_id")
    private ThietBi thietBi;

    @ManyToOne
    @JoinColumn(name = "lich_su_ket_noi_id")
    private LichSuKetNoi lichSuKetNoi;

    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "thong_bao_id")
    private ThongBao thongBao;

    private Boolean xoa;
}
