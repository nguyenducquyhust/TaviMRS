package com.tavi.tavi_mrs.entities.thu_tuc;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
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
@Table(name = "Giay_Phep_Theo_Thu_Tuc", schema = "dbo")
public class GiayPhepTheoThuTuc extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_giay_phep_theo_thu_tuc")
    private Integer idGiayPhepTheoThuTuc;

    @Column(name = "ten_giay_phep_theo_thu_tuc")
    private String tenGiayPhepTheoThuTuc;

    @Column(name = "duong_dan_giay_phep")
    private String duongDanGiayPhep;

    @ManyToOne
    @JoinColumn(name = "thu_tuc_id")
    private ThuTuc thuTuc;

    @ManyToOne
    @JoinColumn(name = "giay_phep_theo_loai_thu_tuc_id")
    private GiayPhepTheoLoaiThuTuc giayPhepTheoLoaiThuTuc;

    private Boolean xoa;
}
