package com.tavi.tavi_mrs.entities.thu_tuc;

import com.tavi.tavi_mrs.entities.base.BaseEntity;
import com.tavi.tavi_mrs.entities.doanh_nghiep.DoanhNghiep;
import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.mo.Mo;
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
@Table(name = "Thu_Tuc", schema = "dbo")
public class ThuTuc extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_thu_tuc")
    private Integer idThuTuc;

    @Column(name = "ten_thu_tuc")
    private String tenThuTuc;

    @ManyToOne
    @JoinColumn(name = "loai_thu_tuc_id")
    private LoaiThuTuc loaiThuTuc;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "giay_phep_khai_thac_id")
    private GiayPhepKhaiThac giayPhepKhaiThac;

    @ManyToOne
    @JoinColumn(name = "doanh_nghiep_id")
    private DoanhNghiep doanhNghiep;

//    @Column(name = "trang_thai")
//    private Integer trangThai;

    private Boolean xoa;
}
